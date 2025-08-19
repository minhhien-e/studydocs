package studydocs.interfaces;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import studydocs.application.DocumentService;
import studydocs.domain.Document;
import studydocs.dto.UploadDocumentRequest;

@RestController
@RequestMapping("/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<?> uploadDocument(
            @RequestPart("data") UploadDocumentRequest data,
            @RequestPart("file") MultipartFile file) {
        Document doc = documentService.createAndUploadDocument(data, file);
        // trả về hiện trạng là UPLOADING (client poll/subscribe để biết khi nào thành UPLOADED)
        return ResponseEntity.accepted().body("Document created with id=" + doc.getId() + " status=UPLOADING");
    }

    // test helper: gửi file mẫu tới upload service trực tiếp (dùng để test flow end-to-end)
//    @PostMapping("/test-upload")
//    public ResponseEntity<?> uploadDocument(@RequestParam Long userId,
//                                        @RequestParam String title,
//                                        @RequestParam String description,
//                                        @RequestParam("file") MultipartFile file) {
//        UploadDocumentRequest req = new UploadDocumentRequest();
//        req.setUserId(userId);
//        req.setTitle(title);
//        req.setDescription(description);
//        Document doc = documentService.createAndUploadDocument(req, file);
//        return ResponseEntity.accepted().body("Test documentId=" + doc.getId());
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Document> getDocumentById(@PathVariable Long id) {
        Document document = documentService.getDocumentById(id);
        return ResponseEntity.ok(document);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Document> updateDocument(@PathVariable Long id,
                                                   @RequestParam String title,
                                                   @RequestParam String description) {
        return ResponseEntity.ok(documentService.updateDocument(id, title, description));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDocument(@PathVariable Long id) {
        documentService.deleteDocument(id);
        return ResponseEntity.ok("Document " + id + " đã bị xóa");
    }

}
