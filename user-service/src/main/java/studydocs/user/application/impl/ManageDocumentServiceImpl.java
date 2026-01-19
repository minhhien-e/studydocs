package studydocs.user.application.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studydocs.user.application.ManageDocumentService;
import studydocs.user.domain.entity.UserEntity;
import studydocs.user.domain.repository.UserRepository;
import studydocs.user.interfaces.model.ApiResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ManageDocumentServiceImpl implements ManageDocumentService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public ApiResponse<Boolean> toggleSaveDocument(UUID userId, UUID documentId, String traceId) {
        log.info("[traceId: {}] Toggling save document {} for user {}", traceId, documentId, userId);

        Optional<UserEntity> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            log.error("[traceId: {}] User not found: {}", traceId, userId);
            // Assuming 404 for not found, and some error code. Adjust as needed.
            return ApiResponse.error(404, 404001, traceId); 
        }

        UserEntity user = userOpt.get();
        if (user.getSavedDocumentIds() == null) {
            user.setSavedDocumentIds(new ArrayList<>());
        }

        boolean isSaved;
        if (user.getSavedDocumentIds().contains(documentId)) {
            user.getSavedDocumentIds().remove(documentId);
            isSaved = false;
            log.info("[traceId: {}] Document {} unsaved for user {}", traceId, documentId, userId);
        } else {
            user.getSavedDocumentIds().add(documentId);
            isSaved = true;
            log.info("[traceId: {}] Document {} saved for user {}", traceId, documentId, userId);
        }

        userRepository.save(user);

        return ApiResponse.success(isSaved, traceId);
    }

    @Override
    @Transactional
    public ApiResponse<Boolean> unsaveDocument(UUID userId, UUID documentId, String traceId) {
        log.info("[traceId: {}] Unsaving document {} for user {}", traceId, documentId, userId);

        Optional<UserEntity> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            log.error("[traceId: {}] User not found: {}", traceId, userId);
            return ApiResponse.error(404, 404001, traceId);
        }

        UserEntity user = userOpt.get();
        if (user.getSavedDocumentIds() == null) {
            user.setSavedDocumentIds(new ArrayList<>());
        }

        boolean wasRemoved = user.getSavedDocumentIds().remove(documentId);
        
        if (wasRemoved) {
            userRepository.save(user);
            log.info("[traceId: {}] Document {} successfully unsaved for user {}", traceId, documentId, userId);
        } else {
            log.warn("[traceId: {}] Document {} was not in saved list for user {}", traceId, documentId, userId);
        }

        return ApiResponse.success(wasRemoved, traceId);
    }

    @Override
    public ApiResponse<List<UUID>> getSavedDocumentIds(UUID userId, String traceId) {
        log.info("[traceId: {}] Getting saved documents for user {}", traceId, userId);

        Optional<UserEntity> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
             log.error("[traceId: {}] User not found: {}", traceId, userId);
            return ApiResponse.error(404, 404001, traceId);
        }

        UserEntity user = userOpt.get();
        List<UUID> savedIds = user.getSavedDocumentIds();
        if (savedIds == null) {
            savedIds = new ArrayList<>();
        }

        return ApiResponse.success(savedIds, traceId);
    }
}
