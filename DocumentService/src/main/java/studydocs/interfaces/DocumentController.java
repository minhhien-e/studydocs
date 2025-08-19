package studydocs.interfaces;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import studydocs.application.DocumentService;
import studydocs.domain.Document;
import studydocs.dto.ApiResponse;
import studydocs.dto.UploadDocumentRequest;

@RestController
@RequestMapping("/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<ApiResponse<String>> uploadDocument(
            @RequestPart("data") UploadDocumentRequest data,
            @RequestPart("file") MultipartFile file) {
        Document doc = documentService.createAndUploadDocument(data, file);
        return ResponseEntity.accepted()
                .body(ApiResponse.success("Document created with id=" + doc.getId() + " status=UPLOADING", null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Document>> getDocumentById(@PathVariable Long id) {
        Document document = documentService.getDocumentById(id);
        return ResponseEntity.ok(ApiResponse.success("Success", document));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Document>> updateDocument(@PathVariable Long id,
                                                                @RequestParam String title,
                                                                @RequestParam String description) {
        Document document = documentService.updateDocument(id, title, description);
        return ResponseEntity.ok(ApiResponse.success("Success", document));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteDocument(@PathVariable Long id) {
        documentService.deleteDocument(id);
        return ResponseEntity.ok(ApiResponse.success("Document " + id + " đã bị xóa", null));
    }
}