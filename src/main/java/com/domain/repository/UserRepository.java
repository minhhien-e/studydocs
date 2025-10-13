package com.domain.repository;

import com.domain.entity.UserEntity;
import com.error.exception.ExceptionMessage;
import io.github.resilience4j.core.functions.Either;

import java.util.Optional;
import java.util.concurrent.CompletionStage;



public interface UserRepository {

    CompletionStage<Boolean> existsByUsername(String username);

    CompletionStage<Boolean> existsByUserId(String id);

    CompletionStage<Either<ExceptionMessage, UserEntity>> save(UserEntity user);

    CompletionStage<Either<ExceptionMessage, Void>> updateUser(UserEntity user);

    CompletionStage<Either<ExceptionMessage, Void>> deleteById(String id);

    CompletionStage<Optional<UserEntity>> findById(String id);

    CompletionStage<Optional<UserEntity>> findByUsername(String username);
}

