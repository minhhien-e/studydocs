package studydocs.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studydocs.domain.Document;
import studydocs.repository.DocumentRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminInterventionService {

    private final DocumentRepository documentRepository;

    @Transactional
    public void deleteUserDocuments(UUID userId) {
        List<Document> documents = documentRepository.findAllByUserIdAndIsDeletedFalse(userId);
        for (Document document : documents) {
            document.markAsDeleted();
        }
        documentRepository.saveAll(documents);
    }

    @Transactional
    public void deleteDocument(UUID documentId) {
        documentRepository.findById(documentId).ifPresent(document -> {
            document.markAsDeleted();
            documentRepository.save(document);
        });
    }
}
