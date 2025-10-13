package com.infrastructure.repository;


import com.domain.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.LocalDate;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@Import(UserMongoRepository.class)
class UserMongoRepositoryTest {

    @Autowired
    private UserMongoRepository userMongoRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    private UserEntity userEntity;

    @BeforeEach
    void setUp() {
        mongoTemplate.dropCollection(UserEntity.class);

        userEntity = new UserEntity(
                "u1",
                "Nguyen Van A",
                "nguyenvana",
                "a@example.com",
                "56789",
                "avatar.png",
                "Male",
                LocalDate.of(1990, 1, 1),
                "Hanoi"
        );
    }

    @Test
    void testSaveAndFindById() throws ExecutionException, InterruptedException {
        userMongoRepository.save(userEntity).toCompletableFuture().get();

        Optional<UserEntity> found =
                userMongoRepository.findById("u1").toCompletableFuture().get();

        assertThat(found).isPresent();
        assertThat(found.get().getUsername()).isEqualTo("nguyenvana");
    }

    @Test
    void testExistsByUsername() throws ExecutionException, InterruptedException {
        mongoTemplate.save(userEntity);

        Boolean exists =
                userMongoRepository.existsByUsername("nguyenvana").toCompletableFuture().get();

        assertThat(exists).isTrue();
    }

    @Test
    void testUpdateUser() throws ExecutionException, InterruptedException {
        mongoTemplate.save(userEntity);

        userEntity.setFullName("Nguyen Van B");
        userMongoRepository.updateUser(userEntity).toCompletableFuture().get();

        UserEntity updated = mongoTemplate.findById("u1", UserEntity.class);
        assertThat(updated.getFullName()).isEqualTo("Nguyen Van B");
    }

    @Test
    void testDeleteById() throws ExecutionException, InterruptedException {
        mongoTemplate.save(userEntity);

        userMongoRepository.deleteById("u1").toCompletableFuture().get();

        Optional<UserEntity> found =
                userMongoRepository.findById("u1").toCompletableFuture().get();
        assertThat(found).isEmpty();
    }
}
