package studydocs.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import studydocs.domain.Document;
import studydocs.exception.DocumentNotFoundException;
import studydocs.exception.DocumentProcessingException;
import studydocs.repository.DocumentRepository;
import studydocs.dto.UploadDocumentRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final RestTemplate restTemplate;
    private final StringRedisTemplate redisTemplate;
    private final ChannelTopic uploadStartedTopic;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Value("${upload.service.url}")
    private String uploadServiceUrl;


    @Transactional
    public Document createAndUploadDocument(UploadDocumentRequest req, MultipartFile file) {
        try {
            // 1) tạo record document với trạng thái UPLOADING
            Document document = new Document(req.getUserId(), req.getTitle(), req.getDescription());
            document.markUploading();
            document = documentRepository.save(document);

            Long documentId = document.getId();
            System.out.println("DocumentService: Tạo document id=" + documentId + " và đặt trạng thái UPLOADING");

            // 2) Publish event started (informational)
            Map<String, Object> startedPayload = new HashMap<>();
            startedPayload.put("documentId", documentId);
            startedPayload.put("userId", req.getUserId());
            startedPayload.put("title", req.getTitle());
            redisTemplate.convertAndSend(uploadStartedTopic.getTopic(), objectMapper.writeValueAsString(startedPayload));

            // 3) Gửi file tới UploadService (multipart) kèm documentId
            // String uploadServiceUrl = "http://localhost:9150/api/upload"
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file", file.getResource());
            body.add("documentId", documentId.toString());

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(uploadServiceUrl, requestEntity, String.class);

            if (!response.getStatusCode().is2xxSuccessful()) {
                // nếu upload thất bại ngay lập tức -> mark failed : trạng thái thất bại (failed)
                document.markFailed("UploadService returned non-2xx");
                documentRepository.save(document);
                throw new DocumentProcessingException("UploadService trả về lỗi: " + response.getStatusCode());
            }

            // trả về document (client sẽ nhận là đang upload; kết quả cuối cùng được cập nhật khi Redis event tới)
            return document;
        } catch (Exception ex) {
            throw new DocumentProcessingException("Lỗi khi upload tài liệu: " + ex.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public Document getDocumentById(Long id) {
        return documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tài liệu " + id));
    }

    @Transactional(readOnly = true)
    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    @Transactional
    public Document updateDocument(Long id, String title, String description) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new DocumentNotFoundException(id));
        document.update(title, description);
        return documentRepository.save(document);
    }

    @Transactional
    public void deleteDocument(Long id) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new DocumentNotFoundException(id));
        document.markAsDeleted();
        documentRepository.save(document);
    }
}