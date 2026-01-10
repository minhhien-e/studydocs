package studydocs.media.api.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import studydocs.media.api.dto.request.DeleteAssetByIdRequest;
import studydocs.media.api.dto.request.GetAssetByIdRequest;
import studydocs.media.api.dto.request.UploadAssetRequest;
import studydocs.media.api.helper.RequestExecutor;
import studydocs.media.api.mapper.AssetMapper;
import studydocs.media.application.dto.projection.AssetProjection;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/assets")
@RequiredArgsConstructor
@Slf4j
public class AssetController {

    private final RequestExecutor requestExecutor;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        var request = new UploadAssetRequest(file);
        return requestExecutor.executeWithCurrentUserAndMapView(
                AssetMapper::toCommand,
                request,
                projection -> AssetMapper.toView((AssetProjection) projection),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFile(@PathVariable("id") UUID id) {
        var request = GetAssetByIdRequest.builder().id(id).build();
        return requestExecutor.executeAndMapView(
                AssetMapper::toQuery,
                request,
                projection -> AssetMapper.toView((AssetProjection) projection),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable("id") UUID id) {
        var request = DeleteAssetByIdRequest.builder().id(id).build();
        return requestExecutor.executeWithCurrentUser(
                AssetMapper::toCommand,
                request,
                HttpStatus.OK
        );
    }

}
