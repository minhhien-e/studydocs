package com.infrastructure.repository;

import com.domain.entity.UserEntity;
import com.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.error.ErrorCode.*;
import static com.error.factory.InfrastructureExceptionFactory.custom;
//import static com.error.infrastructure.InfrastructureExceptionFactory.custom;

@Repository
@RequiredArgsConstructor
public class UserMongoRepository implements UserRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public boolean existsByUsername(String username) {
        try {
            Query query = new Query(Criteria.where("username").is(username));
            return mongoTemplate.exists(query, UserEntity.class);
        } catch (Throwable t) {
            throw custom(SAVE_FAILED, "existsByUsername", t);
        }
    }

    @Override
    public boolean existsByUserId(String id) {
        try {
            Query query = new Query(Criteria.where("id").is(id));
            return mongoTemplate.exists(query, UserEntity.class);
        } catch (Throwable t) {
            throw custom(SAVE_FAILED, "existsByUserId", t);
        }
    }

    @Override
    public UserEntity save(UserEntity user) {
        try {
            return mongoTemplate.save(user);
        } catch (Throwable t) {
            throw custom(SAVE_FAILED, "save", t);
        }
    }

    @Override
    public void updateUser(UserEntity user) {
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
        } catch (Throwable t) {
            throw custom(UPDATE_FAILED, "updateUser", t);
        }
    }

    @Override
    public void deleteById(String id) {
        try {
            Query query = new Query(Criteria.where("id").is(id));
            mongoTemplate.remove(query, UserEntity.class);
        } catch (Throwable t) {
            throw custom(DELETE_FAILED, "deleteById", t);
        }
    }

    @Override
    public Optional<UserEntity> findById(String id) {
        try {
            return Optional.ofNullable(mongoTemplate.findById(id, UserEntity.class));
        } catch (Throwable t) {
            throw custom(SAVE_FAILED, "findById", t);
        }
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        try {
            Query query = new Query(Criteria.where("username").is(username));
            return Optional.ofNullable(mongoTemplate.findOne(query, UserEntity.class));
        } catch (Throwable t) {
            throw custom(SAVE_FAILED, "findByUsername", t);
        }
    }

    @Override
    public List<UserEntity> findAll() {
        try {
            return mongoTemplate.findAll(UserEntity.class);
        } catch (Throwable t) {
            throw custom(SAVE_FAILED, "findAll", t);
        }
    }

    @Override
    public boolean existsById(String id) {
        try {
            Query query = new Query(Criteria.where("id").is(id));
            return mongoTemplate.exists(query, UserEntity.class);
        } catch (Throwable t) {
            throw custom(SAVE_FAILED, "existsById", t);
        }
    }

    @Override
    public long count() {
        try {
            return mongoTemplate.count(new Query(), UserEntity.class);
        } catch (Throwable t) {
            throw custom(SAVE_FAILED, "count", t);
        }
    }

}
