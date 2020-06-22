package com.example.bootApp.service;

import com.example.bootApp.model.Users;

import java.util.List;

public interface UserService {
    void save(Users users);
    List<Users> getAll();
    Users get(Long id);
    void delete(Long id);
    Users findByUsername(String username);
    public boolean addUser(Users user);
    boolean activateUser(String code);
}
