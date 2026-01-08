package studydocs.media.api.mapper;

import org.springframework.web.multipart.MultipartFile;
import studydocs.media.api.dto.request.DeleteFileByIdRequest;
import studydocs.media.api.dto.request.GetFileByIdRequest;
import studydocs.media.api.dto.request.UploadFileRequest;
import studydocs.media.api.dto.view.FileView;
import studydocs.media.api.dto.view.PreviewDataView;
import studydocs.media.application.dto.command.DeleteFileByIdCommand;
import studydocs.media.application.dto.command.UploadFileCommand;
import studydocs.media.application.dto.projection.FileProjection;
import studydocs.media.application.dto.query.GetFileByIdQuery;

import java.util.UUID;

public class FileMapper {
    /// Command
    public static UploadFileCommand toCommand(UUID userId, UploadFileRequest request) {
        MultipartFile file = request.file();
        return UploadFileCommand.builder()
                .fileContent(file)
                .fileName(file.getOriginalFilename())
                .fileSize(file.getSize())
                .uploaderId(userId)
                .build();
    }

    public static DeleteFileByIdCommand toCommand(UUID userId, DeleteFileByIdRequest request) {
        return DeleteFileByIdCommand.builder()
                .fileId(request.id())
                .userId(userId)
                .build();

    }

    /// Query
    public static GetFileByIdQuery toQuery(GetFileByIdRequest request) {
        return GetFileByIdQuery.builder()
                .id(request.id())
                .build();
    }

    /// View
    public static FileView toView(FileProjection projection) {
        return FileView.builder()
                .id(projection.id())
                .fileName(projection.fileName())
                .fileSize(projection.fileSize())
                .contentType(projection.contentType())
                .totalPage(projection.totalPage())
                .previewDataView(PreviewDataView.builder()
                        .baseUrl(projection.previewData().baseUrl())
                        .key(projection.previewData().key())
                        .build())
                .downloadUrl(projection.downloadUrl())
                .build();
    }

}
