package com.example.bootApp.controller;

import com.example.bootApp.model.Role;
import com.example.bootApp.model.User;
import com.example.bootApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping
    public String userList(Model model) {
        model.addAttribute("userList", userService.getAll());
        return "userList";
    }

    @RequestMapping("{user}")
    public String userEditFrom(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        System.out.println(user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }
    @PostMapping("{id}")
    public String userSave(
            @RequestParam String username,
            @PathVariable(name = "id") Long id,
            @RequestParam Set<Role> roles
    ) {
        User user = userService.get(id);
        user.setUsername(username);
        user.setRoles(roles);
        System.out.println(user);

        userService.save(user);
        return "redirect:/user";
    }
}
