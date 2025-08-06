package studydocs.notificationservice.application.port.input.dto.paging;

public record SliceInput<T>(T request, int limit) {
}
