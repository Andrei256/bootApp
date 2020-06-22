package com.example.bootApp.repository;

import com.example.bootApp.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUsername(String username);

    Users findByActivationCode(String code);
}
