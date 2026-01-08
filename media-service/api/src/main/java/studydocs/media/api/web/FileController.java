package studydocs.media.api.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import studydocs.media.api.dto.request.DeleteFileByIdRequest;
import studydocs.media.api.dto.request.GetFileByIdRequest;
import studydocs.media.api.dto.request.UploadFileRequest;
import studydocs.media.api.helper.RequestExecutor;
import studydocs.media.api.mapper.FileMapper;
import studydocs.media.application.dto.projection.FileProjection;
import studydocs.media.application.port.in.provider.CurrentTraceIdProvider;
import studydocs.media.application.port.in.provider.CurrentUserProviderPort;
import studydocs.media.application.service.orchestrator.UploadAndGetFileOrchestrator;
import studydocs.media.shared.web.ApiResponse;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
@Slf4j
public class FileController {

    private final RequestExecutor requestExecutor;
    private final UploadAndGetFileOrchestrator uploadAndGetFileOrchestrator;
    private final CurrentTraceIdProvider currentTraceIdProvider;
    private final CurrentUserProviderPort currentUserProvider;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        var request = new UploadFileRequest(file);
        var result = uploadAndGetFileOrchestrator.uploadFile(FileMapper.toCommand(currentUserProvider.getCurrentUserId(), request));
        return ResponseEntity.status(201).body(ApiResponse.success(result, currentTraceIdProvider.getCurrentTraceId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFile(@PathVariable("id") UUID id) {
        var request = GetFileByIdRequest.builder().id(id).build();
        return requestExecutor.executeAndMapView(
                FileMapper::toQuery,
                request,
                projection -> FileMapper.toView((FileProjection) projection),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable("id") UUID id) {
        var request = DeleteFileByIdRequest.builder().id(id).build();
        return requestExecutor.executeWithCurrentUser(
                FileMapper::toCommand,
                request,
                HttpStatus.OK
        );
    }

}
