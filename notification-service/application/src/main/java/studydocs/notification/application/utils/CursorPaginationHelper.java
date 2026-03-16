package studydocs.notification.application.utils;

import studydocs.notification.application.dto.base.CursorPaginationResult;

import java.util.List;
import java.util.function.Function;


public final class CursorPaginationHelper {

    private CursorPaginationHelper() {
    }

    public static <T> CursorPaginationResult<T> buildResult(
            List<T> fetchedData,
            int requestedLimit,
            long totalCount,
            Function<T, Object> cursorExtractor
    ) {
        boolean hasNext = fetchedData.size() > requestedLimit;

        List<T> dataToReturn = hasNext
                ? fetchedData.subList(0, requestedLimit)
                : fetchedData;

        Object nextCursor = hasNext && !dataToReturn.isEmpty()
                ? cursorExtractor.apply(dataToReturn.get(dataToReturn.size() - 1))
                : null;

        return CursorPaginationResult.<T>builder()
                .data(dataToReturn)
                .nextCursor(nextCursor)
                .total(totalCount)
                .hasNext(hasNext)
                .build();
    }

    public static <S, T> CursorPaginationResult<T> buildResultWithMapping(
            List<S> fetchedData,
            int requestedLimit,
            long totalCount,
            Function<S, Object> cursorExtractor,
            Function<S, T> mapper
    ) {
        boolean hasNext = fetchedData.size() > requestedLimit;

        List<S> sourceDataToReturn = hasNext
                ? fetchedData.subList(0, requestedLimit)
                : fetchedData;

        List<T> mappedData = sourceDataToReturn.stream()
                .map(mapper)
                .toList();

        Object nextCursor = hasNext && !sourceDataToReturn.isEmpty()
                ? cursorExtractor.apply(sourceDataToReturn.get(sourceDataToReturn.size() - 1))
                : null;

        return CursorPaginationResult.<T>builder()
                .data(mappedData)
                .nextCursor(nextCursor)
                .total(totalCount)
                .hasNext(hasNext)
                .build();
    }
}
