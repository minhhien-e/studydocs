package com.infrastructure.repository;

import com.domain.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.time.LocalDate;
import java.util.List;
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

    // ================================
    // existsByUsername
    // ================================
    @Test
    void testExistsByUsername_ShouldReturnTrue() {
        when(mongoTemplate.exists(any(Query.class), eq(UserEntity.class))).thenReturn(true);

        boolean result = repository.existsByUsername("john");

        assertTrue(result);
        verify(mongoTemplate).exists(any(Query.class), eq(UserEntity.class));
    }

    // ================================
    // existsByUserId
    // ================================
    @Test
    void testExistsByUserId_ShouldReturnFalse() {
        when(mongoTemplate.exists(any(Query.class), eq(UserEntity.class))).thenReturn(false);

        boolean result = repository.existsByUserId("123");

        assertFalse(result);
        verify(mongoTemplate).exists(any(Query.class), eq(UserEntity.class));
    }

    // ================================
    // save()
    // ================================
    @Test
    void testSave_ShouldReturnSavedUser() {

        UserEntity user = new UserEntity(null, "John Doe", "john", "john@example.com",
                "123456789", null, "M", LocalDate.of(1990, 1, 1), "Address");

        when(mongoTemplate.save(user)).thenReturn(
                new UserEntity("1", "John Doe", "john",
                        "john@example.com", "123456789", null,
                        "M", LocalDate.of(1990, 1, 1), "Address")
        );

        UserEntity saved = repository.save(user);

        assertEquals("1", saved.getId());
        verify(mongoTemplate).save(user);
    }

    // ================================
    // updateUser
    // ================================
    @Test
    void testUpdateUser_ShouldCallUpdateFirst() {

        UserEntity user = new UserEntity("1", "John", "john",
                "john@example.com", "123", "/a.jpg", "M",
                LocalDate.of(1990, 1, 1), "Address");

        repository.updateUser(user);

        ArgumentCaptor<Query> queryCaptor = ArgumentCaptor.forClass(Query.class);
        ArgumentCaptor<Update> updateCaptor = ArgumentCaptor.forClass(Update.class);

        verify(mongoTemplate).updateFirst(queryCaptor.capture(), updateCaptor.capture(), eq(UserEntity.class));

        assertEquals("1", queryCaptor.getValue().getQueryObject().get("id"));
        assertEquals("John", updateCaptor.getValue().getUpdateObject().get("fullName"));
    }

    // ================================
    // deleteById
    // ================================
    @Test
    void testDeleteById_ShouldCallRemove() {
        repository.deleteById("10");

        ArgumentCaptor<Query> queryCaptor = ArgumentCaptor.forClass(Query.class);
        verify(mongoTemplate).remove(queryCaptor.capture(), eq(UserEntity.class));

        assertEquals("10", queryCaptor.getValue().getQueryObject().get("id"));
    }

    // ================================
    // findById
    // ================================
    @Test
    void testFindById_ShouldReturnUser() {

        UserEntity user = new UserEntity("1", "John", "john",
                "john@example.com", "123", null, "M", LocalDate.now(), "Address");

        when(mongoTemplate.findById("1", UserEntity.class)).thenReturn(user);

        Optional<UserEntity> result = repository.findById("1");

        assertTrue(result.isPresent());
        assertEquals("John", result.get().getFullName());
    }

    // ================================
    // findByUsername
    // ================================
    @Test
    void testFindByUsername_ShouldReturnUser() {

        UserEntity user = new UserEntity("1", "John", "john",
                "john@example.com", "123", null, "M", LocalDate.now(), "Address");

        when(mongoTemplate.findOne(any(Query.class), eq(UserEntity.class))).thenReturn(user);

        Optional<UserEntity> result = repository.findByUsername("john");

        assertTrue(result.isPresent());
        assertEquals("john", result.get().getUsername());
    }

    // ================================
    // findAll
    // ================================
    @Test
    void testFindAll_ShouldReturnUserList() {

        List<UserEntity> mockUsers = List.of(
                new UserEntity("1", "A", "a", "a@a.com","1", null,"M",LocalDate.now(),"x"),
                new UserEntity("2", "B", "b", "b@b.com","2", null,"F",LocalDate.now(),"y")
        );

        when(mongoTemplate.findAll(UserEntity.class)).thenReturn(mockUsers);

        List<UserEntity> result = repository.findAll();

        assertEquals(2, result.size());
        verify(mongoTemplate).findAll(UserEntity.class);
    }

    // ================================
    // existsById
    // ================================
    @Test
    void testExistsById_ShouldReturnTrue() {

        when(mongoTemplate.exists(any(Query.class), eq(UserEntity.class))).thenReturn(true);

        boolean result = repository.existsById("1");

        assertTrue(result);
    }

    // ================================
    // count
    // ================================
    @Test
    void testCount_ShouldReturnCount() {

        when(mongoTemplate.count(any(Query.class), eq(UserEntity.class))).thenReturn(5L);

        long result = repository.count();

        assertEquals(5, result);
        verify(mongoTemplate).count(any(Query.class), eq(UserEntity.class));
    }
}
