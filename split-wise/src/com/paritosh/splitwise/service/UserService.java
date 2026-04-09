package com.paritosh.splitwise.service;

import com.paritosh.splitwise.repository.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String addUser(String userId, String userName) {
        if (userRepository.userExist(userId)) {
            return "user already exists";
        }
        userRepository.addUser(userId, userName);
        return "user added successfully";
    }
}
