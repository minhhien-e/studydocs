package studydocs.notification.application.dto.base;

import lombok.Builder;

import java.util.List;
@Builder
public record CursorPaginationResult<T>(List<T> data,
                                        Object nextCursor,
                                        long total,
                                        boolean hasNext) {
}
