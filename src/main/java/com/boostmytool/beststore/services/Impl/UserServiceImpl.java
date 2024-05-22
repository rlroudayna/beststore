package com.boostmytool.beststore.services.Impl;

import com.boostmytool.beststore.models.RegistrationDto;
import com.boostmytool.beststore.models.UserEntity;
import com.boostmytool.beststore.services.UserRepository;
import com.boostmytool.beststore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public void addUser(RegistrationDto registrationDto) {
        UserEntity user = new UserEntity();
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));

        userRepository.save(user);

    }



    @Override
    public UserEntity findFirstByUsername(String username) {
        try {
            return userRepository.findFirstByUsername(username);

        }catch (Exception exception){
            System.out.println(exception.getMessage());
            return null;
        }
}

    @Override
    public UserEntity findByEmail(String email) {
        return null;
    }


}
