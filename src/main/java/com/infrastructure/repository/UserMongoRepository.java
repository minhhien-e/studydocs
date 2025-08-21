package com.infrastructure.repository;

import com.domain.entity.UserEntity;
import com.domain.repository.UserRepository;
import io.github.resilience4j.core.functions.Either;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Repository
public class UserMongoRepository implements UserRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public UserMongoRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public CompletionStage<Boolean> existsByUsername(String username) {
        return CompletableFuture.supplyAsync(() -> {
            Query query = new Query(Criteria.where("username").is(username));
            return mongoTemplate.exists(query, UserEntity.class);
        });
    }

    @Override
    public CompletionStage<Boolean> existsByUserId(String id) {
        return CompletableFuture.supplyAsync(() -> {
            Query query = new Query(Criteria.where("id").is(id));
            return mongoTemplate.exists(query, UserEntity.class);
        });
    }

    @Override
    public CompletionStage<Either<String, UserEntity>> save(UserEntity user) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                UserEntity saved = mongoTemplate.save(user);
                return Either.right(saved);
            } catch (Exception e) {
                return Either.left("Error saving user: " + e.getMessage());
            }
        });
    }

    @Override
    public CompletionStage<Either<String, Void>> updateUser(UserEntity user) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Query query = new Query(Criteria.where("id").is(user.getId()));
                Update update = new Update()
                        .set("fullName", user.getFullName())
                        .set("username", user.getUsername())
                        .set("email", user.getEmail())
                        .set("phoneNumber", user.getPhoneNumber())
                        .set("avatarUrl", user.getAvatarUrl())
                        .set("gender", user.getGender())
                        .set("dateOfBirth", user.getDateOfBirth())
                        .set("address", user.getAddress());

                mongoTemplate.updateFirst(query, update, UserEntity.class);
                return Either.right(null);
            } catch (Exception e) {
                return Either.left("Error updating user: " + e.getMessage());
            }
        });
    }

    @Override
    public CompletionStage<Either<String, Void>> deleteById(String id) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Query query = new Query(Criteria.where("id").is(id));
                mongoTemplate.remove(query, UserEntity.class);
                return Either.right(null);
            } catch (Exception e) {
                return Either.left("Error deleting user: " + e.getMessage());
            }
        });
    }

    @Override
    public CompletionStage<Optional<UserEntity>> findById(String id) {
        return CompletableFuture.supplyAsync(() ->
                Optional.ofNullable(mongoTemplate.findById(id, UserEntity.class))
        );
    }

    @Override
    public CompletionStage<Optional<UserEntity>> findByUsername(String username) {
        return CompletableFuture.supplyAsync(() -> {
            Query query = new Query(Criteria.where("username").is(username));
            return Optional.ofNullable(mongoTemplate.findOne(query, UserEntity.class));
        });
    }
}
