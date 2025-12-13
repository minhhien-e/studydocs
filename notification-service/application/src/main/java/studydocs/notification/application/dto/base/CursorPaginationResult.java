package studydocs.notification.application.dto.base;

import java.util.List;

public record CursorPaginationResult<T>(List<T> data,
                                        Object nextCursor,
                                        long total,
                                        boolean hasNext) {
}
