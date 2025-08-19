package studydocs.messaging;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import studydocs.repository.DocumentRepository;
import studydocs.domain.Document;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UploadSuccessListener {

    private final DocumentRepository documentRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = "upload_success")
    public void handleUploadSuccess(String body) {
        try {
            JsonNode node = objectMapper.readTree(body);
            Long documentId = node.has("documentId") && !node.get("documentId").isNull()
                    ? node.get("documentId").asLong() : null;
            String fileUrl = node.has("fileUrl") ? node.get("fileUrl").asText(null) : null;

            if (documentId != null && fileUrl != null) {
                boolean updated = false;

                for (int i = 0; i < 5; i++) { // Thử tối đa 5 lần
                    Optional<Document> opt = documentRepository.findById(documentId);
                    if (opt.isPresent()) {
                        Document doc = opt.get();
                        doc.markUploaded(fileUrl);
                        documentRepository.save(doc);
                        System.out.println("DocumentService: Đã cập nhật document " + documentId + " thành UPLOADED");
                        updated = true;
                        break;
                    } else {
                        System.out.println("DocumentService: Không tìm thấy documentId = " + documentId + " (retry " + (i + 1) + ")");
                        Thread.sleep(500); // Chờ 0.5s trước khi thử lại
                    }
                }

                if (!updated) {
                    System.err.println("DocumentService: Hết số lần retry nhưng vẫn không tìm thấy documentId = " + documentId);
                }
            } else {
                System.out.println("UploadSuccessListener: Payload không đủ: " + body);
            }
        } catch (Exception ex) {
            System.err.println("UploadSuccessListener lỗi khi parse message: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}