package com.domain.result;

import com.domain.exception.ExceptionMessage;
import io.github.resilience4j.core.functions.Either;

import java.util.concurrent.CompletableFuture;

public class ResultHelper {

    private ResultHelper() {
        // private constructor để chặn khởi tạo
    }

    /**
     * Tạo kết quả thành công (OperationResult)
     */
    public static <T> CompletableFuture<Either<ExceptionMessage, OperationResult>> success(String message, T data) {
        return CompletableFuture.completedFuture(
                Either.right(OperationResult.of(message, data))
        );
    }

    /**
     * Tạo kết quả lỗi (ExceptionMessage)
     */
    public static <T> CompletableFuture<Either<ExceptionMessage, T>> failure(ExceptionMessage error) {
        return CompletableFuture.completedFuture(Either.left(error));
    }
}

