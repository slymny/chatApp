package com.bunq.chatApp.services;

import com.bunq.chatApp.entities.Group;
import com.bunq.chatApp.entities.Message;
import com.bunq.chatApp.entities.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
public class MessageServiceImplTest {
    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @Test
    public void testAddMessage() {
        User user1 = userService.createUser("user1", "user", "first");
        Group group1 = groupService.createGroup("group1");
        messageService.addMessage(group1.getId(), user1.getUsername(), "message1");
        List<Message> messages = messageService.getAllMessages(group1.getId());
        assertEquals("message1", messages.get(0).getText());
    }

    @Test
    public void testGetAllMessages() {
        User user1 = userService.createUser("user1", "user", "first");
        User user2 = userService.createUser("user2", "user", "second");
        Group group1 = groupService.createGroup("group1");
        messageService.addMessage(group1.getId(), user1.getUsername(), "message1");
        messageService.addMessage(group1.getId(), user2.getUsername(), "message2");
        List<Message> messages = messageService.getAllMessages(group1.getId());
        assertEquals(2, messages.size());
        assertTrue(messages.contains(new Message(group1, user1, "message1")));
        assertTrue(messages.contains(new Message(group1, user2, "message2")));
    }
}

