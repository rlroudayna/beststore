package com.boostmytool.beststore.services;

import com.boostmytool.beststore.models.UserEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

   // UserEntity findFirstByEmail(String email);
    UserEntity findFirstByUsername(String username);
}
