package com.example.bootApp.controller;

import com.example.bootApp.model.Role;
import com.example.bootApp.model.Users;
import com.example.bootApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/registration")
    public String registrationView() {
        return "registration";
    }
    @PostMapping("/registration")
    public String addUser(Users users) {
        Users usersFromDB = userRepository.findByUsername(users.getUsername());
        if (usersFromDB == null) {
            users.setActive(true);
            users.setRoles(Collections.singleton(Role.USER));
            userRepository.save(users);
            return "redirect:/login";
        }
        return "registration";
    }
}
