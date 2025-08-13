package studydocs.notificationservice.shared.paging;

public record SliceInput<T>(T request, int limit) {
}
