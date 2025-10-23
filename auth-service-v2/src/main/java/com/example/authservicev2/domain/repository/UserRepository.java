package com.example.authservicev2.domain.repository;


import com.example.authservicev2.domain.model.entities.User;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUserName(String userName);

    boolean existsByEmail(String email);

    boolean existsByUserName(String userName);

    Optional<User> findByProviderId(String providerId);

    Optional<User> findByUserNameAndPassword(String userName, String password);
}
