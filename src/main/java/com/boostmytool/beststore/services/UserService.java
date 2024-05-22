package com.boostmytool.beststore.services;

import com.boostmytool.beststore.models.RegistrationDto;
import com.boostmytool.beststore.models.UserEntity;

public interface UserService {
    void addUser(RegistrationDto registrationDto);
   // UserEntity findByEmail(String email);

    UserEntity findFirstByUsername(String username);

    UserEntity findByEmail(String email);

    //  UserEntity findFirstByEmail(String email);

   // UserEntity findByEmail(String email);

    // UserEntity findFirstByEmail(String email);
}
