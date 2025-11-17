package com.infrastructure.repository;

import com.domain.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserMongoRepositoryTest {

    private MongoTemplate mongoTemplate;
    private UserMongoRepository repository;

    @BeforeEach
    void setUp() {
        mongoTemplate = mock(MongoTemplate.class);
        repository = new UserMongoRepository(mongoTemplate);
    }

    @Test
    void testExistsByUsername_ShouldReturnTrue() {
        String username = "john";
        when(mongoTemplate.exists(any(Query.class), eq(UserEntity.class))).thenReturn(true);

        boolean result = repository.existsByUsername(username);

        assertTrue(result);
        verify(mongoTemplate, times(1)).exists(any(Query.class), eq(UserEntity.class));
    }

    @Test
    void testSave_ShouldReturnSavedUser() {
        UserEntity user = new UserEntity(null, "John Doe", "john", "john@example.com",
                "123456789", null, "M", LocalDate.of(1990, 1, 1), "Address");
        when(mongoTemplate.save(user)).thenReturn(new UserEntity("1", "John Doe", "john",
                "john@example.com", "123456789", null, "M", LocalDate.of(1990,1,1), "Address"));

        UserEntity saved = repository.save(user);

        assertNotNull(saved.getId());
        assertEquals("John Doe", saved.getFullName());
        verify(mongoTemplate, times(1)).save(user);
    }

    @Test
    void testUpdateUser_ShouldCallUpdateFirst() {
        UserEntity user = new UserEntity("1", "John Doe", "john", "john@example.com",
                "123456789", null, "M", LocalDate.of(1990,1,1), "Address");

        repository.updateUser(user);

        ArgumentCaptor<Query> queryCaptor = ArgumentCaptor.forClass(Query.class);
        ArgumentCaptor<Update> updateCaptor = ArgumentCaptor.forClass(Update.class);

        verify(mongoTemplate, times(1)).updateFirst(queryCaptor.capture(),
                updateCaptor.capture(), eq(UserEntity.class));

        assertEquals("1", queryCaptor.getValue().getQueryObject().get("_id")); // Mongo id field mapping
        assertEquals("John Doe", updateCaptor.getValue().getUpdateObject().get("fullName"));
    }

    @Test
    void testFindById_ShouldReturnUser() {
        String id = "1";
        UserEntity user = new UserEntity(id, "John Doe", "john", "john@example.com",
                "123456789", null, "M", LocalDate.of(1990,1,1), "Address");

        when(mongoTemplate.findById(id, UserEntity.class)).thenReturn(user);

        Optional<UserEntity> result = repository.findById(id);

        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getFullName());
        verify(mongoTemplate, times(1)).findById(id, UserEntity.class);
    }

    @Test
    void testDeleteById_ShouldCallRemove() {
        String id = "1";

        repository.deleteById(id);

        ArgumentCaptor<Query> queryCaptor = ArgumentCaptor.forClass(Query.class);
        verify(mongoTemplate, times(1)).remove(queryCaptor.capture(), eq(UserEntity.class));

        assertEquals("1", queryCaptor.getValue().getQueryObject().get("_id"));
    }

}
