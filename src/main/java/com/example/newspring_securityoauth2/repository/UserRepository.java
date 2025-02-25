package com.example.newspring_securityoauth2.repository;

import com.example.newspring_securityoauth2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findAllByEmail(String email);
}
