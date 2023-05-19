package com.bunq.chatApp.services;

import com.bunq.chatApp.entities.User;
import com.bunq.chatApp.exception.NotFoundException;
import com.bunq.chatApp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public User createUser(String username, String firstName, String lastName) {
        User isExist = userRepository.findByUsername(username);
        if(isExist != null)
            throw new IllegalArgumentException("Username is already exist!");
        User user = new User(username, firstName, lastName);
        return userRepository.save(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getUserByUserId(Long userId) throws NotFoundException {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User is not found!"));
    }
}
