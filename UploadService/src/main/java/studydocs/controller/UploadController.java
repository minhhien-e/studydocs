package studydocs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import studydocs.dto.ApiResponse;
import studydocs.response.UploadResponse;
import studydocs.service.UploadService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/upload")
@RequiredArgsConstructor
public class UploadController {

    private final UploadService uploadService;
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping
    public ResponseEntity<ApiResponse<UploadResponse>> upload(@RequestParam("file") MultipartFile file,
                                                              @RequestParam(value = "documentId", required = false) Long documentId) {
        try {
            UploadResponse response = uploadService.uploadFile(file);

            // Nếu có documentId => publish event thành công
            if (documentId != null) {
                Map<String, Object> payload = new HashMap<>();
                payload.put("documentId", documentId);
                payload.put("fileUrl", response.getUrl());
                String json;
                try {
                    json = objectMapper.writeValueAsString(payload);
                } catch (Exception e) {
                    json = "{\"documentId\":" + documentId + ",\"fileUrl\":\"" + response.getUrl() + "\"}";
                }
                rabbitTemplate.convertAndSend("upload_success", json);
                System.out.println("UploadService: Published upload_success for documentId=" + documentId);
            }

            return ResponseEntity.ok(ApiResponse.success("Upload successful", response));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error(500, "UPLOAD_ERROR", "Upload failed: " + e.getMessage()));
        }
    }
}