package com.example.bootApp.service;

import com.example.bootApp.model.User;

import java.util.List;

public interface UserService {
    void save(User user);
    List<User> getAll();
    User get(Long id);
    void delete(Long id);
    User findByUsername(String username);
    public boolean addUser(User user);
    boolean activateUser(String code);
}
