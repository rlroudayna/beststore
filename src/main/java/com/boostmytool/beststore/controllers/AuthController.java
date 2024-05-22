package com.boostmytool.beststore.controllers;


import com.boostmytool.beststore.models.RegistrationDto;
import com.boostmytool.beststore.models.UserEntity;
import com.boostmytool.beststore.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    @Autowired
    public UserService userService;


    @GetMapping("/login")
    public String loginPage(){
        return "/products/login";
    }


    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "/products/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") RegistrationDto user, BindingResult result, Model model) {
        UserEntity existingUserEmail = userService.findByEmail(user.getEmail());
        if (existingUserEmail != null && existingUserEmail.getEmail() != null && !existingUserEmail.getEmail().isEmpty()) {
            return"redirect:/register?fail";

        }
        UserEntity existingUserUsername = userService.findFirstByUsername(user.getUsername());
        if (existingUserUsername != null && existingUserUsername.getUsername() != null && !existingUserUsername.getUsername().isEmpty()) {
            return"redirect:/register?fail";

        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
        return "/products/register";
        }
        userService.addUser(user);
        return "redirect:/products?success";
    }


}
