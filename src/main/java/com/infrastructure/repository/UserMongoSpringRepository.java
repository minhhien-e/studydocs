package com.infrastructure.repository;

import com.domain.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserMongoSpringRepository extends MongoRepository<UserEntity, String> {
    Optional<UserEntity> findByUsername(String username);

}