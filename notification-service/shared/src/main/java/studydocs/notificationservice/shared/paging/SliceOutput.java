package studydocs.notificationservice.shared.paging;

import java.util.List;

public record SliceOutput<T>(List<T> content, boolean hasNext) {
}
