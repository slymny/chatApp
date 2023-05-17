package com.bunq.chatApp.services;

import com.bunq.chatApp.entities.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class UserServiceImplTest {
    @Autowired
    private UserService userService;

    @Test
    public void testCreateUser() {
        User user = userService.createUser("user1", "user", "first");
        assertNotNull(user.getId());
        assertEquals("user1", user.getUsername());
    }

    @Test
    public void testGetUserByUsername() {
        User user1 = userService.createUser("user1", "user", "first");
        User user2 = userService.createUser("user2", "user", "second");
        User foundUser = userService.getUserByUsername("user1");
        assertEquals(user1, foundUser);
        assertNotEquals(user2, foundUser);
    }
}

