package com.bunq.chatApp.services;

import com.bunq.chatApp.entities.User;
import com.bunq.chatApp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public User createUser(String username, String firstName, String lastName) {
        User user = new User(username, firstName, lastName);
        return userRepository.save(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
