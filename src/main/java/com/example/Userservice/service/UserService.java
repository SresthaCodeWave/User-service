package com.example.Userservice.service;

import com.example.Userservice.entity.User;

import java.util.Optional;

public interface UserService {

    public Iterable<User> findUserList();
    public void addOrUpdateUser(User user);
    public void deleteUser(String userId);
    public Optional<User> findUserById(String userId);
}
