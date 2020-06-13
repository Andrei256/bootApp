package com.example.bootApp.controller;

import com.example.bootApp.model.Role;
import com.example.bootApp.model.Users;
import com.example.bootApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
//@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping
    public String userList(Model model) {
        model.addAttribute("userList", userService.getAll());
        return "userList";
    }

    @RequestMapping("{user}")
    public String userEditFrom(@PathVariable Users user, Model model) {
        model.addAttribute("user", user);
        System.out.println(user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
//            @RequestParam("userId") Users user
            @ModelAttribute("user") Users user
    ) {
        user.setUsername(username);
        System.out.println(user);
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userService.save(user);
        return "redirect:/user";
    }
}
