package com.bunq.chatApp.services;

import com.bunq.chatApp.entities.User;
import com.bunq.chatApp.exception.NotFoundException;

public interface UserService {
    User createUser(String username, String firstName, String lastName);
    User getUserByUsername(String username);
    User getUserByUserId(Long userId) throws NotFoundException;
}
