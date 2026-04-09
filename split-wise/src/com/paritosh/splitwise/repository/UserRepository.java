package com.paritosh.splitwise.repository;

import com.paritosh.splitwise.model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserRepository {
    List<User> users = new ArrayList<>();
    Set<String> userIds = new HashSet<>();

    public void addUser(String userId, String userName) {
        User user = new User(userId, userName);
        users.add(user);
        userIds.add(userId);
    }

    public boolean userExist(String userId) {
        return userIds.contains(userId);
    }
}
