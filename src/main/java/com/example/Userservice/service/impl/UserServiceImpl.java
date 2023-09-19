package com.example.Userservice.service.impl;

import com.example.Userservice.entity.User;
import com.example.Userservice.repository.UserRepository;
import com.example.Userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserRepository userRepository;

    @Override
    public Iterable<User> findUserList() {
        return userRepository.findAll();
    }

    @Override
    public void addOrUpdateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(String emailId) {
        userRepository.deleteById(emailId);
    }

    @Override
    public Optional<User> findUserById(String emailId) {
        return userRepository.findById(emailId);
    }
}
