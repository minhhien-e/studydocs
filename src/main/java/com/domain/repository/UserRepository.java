package com.domain.repository;

import com.domain.entity.UserEntity;
import io.github.resilience4j.core.functions.Either;

import java.util.Optional;
import java.util.concurrent.CompletionStage;

public interface UserRepository {

    CompletionStage<Boolean> existsByUsername(String username);

    CompletionStage<Boolean> existsByUserId(String id);

    CompletionStage<Either<String, UserEntity>> save(UserEntity user);

    CompletionStage<Either<String, Void>> updateUser(UserEntity user);

    CompletionStage<Either<String, Void>> deleteById(String id);

    CompletionStage<Optional<UserEntity>> findById(String id);

    CompletionStage<Optional<UserEntity>> findByUsername(String username);
}
