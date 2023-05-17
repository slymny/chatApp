package com.bunq.chatApp.services;

import com.bunq.chatApp.entities.User;

public interface UserService {
    User createUser(String username, String firstName, String lastName);
    User getUserByUsername(String username);
}
