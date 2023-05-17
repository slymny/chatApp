package com.bunq.chatApp.controllers;

import com.bunq.chatApp.entities.Group;
import com.bunq.chatApp.entities.Message;
import com.bunq.chatApp.entities.User;
import com.bunq.chatApp.services.GroupService;
import com.bunq.chatApp.services.MessageService;
import com.bunq.chatApp.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class ChatController {
    private UserService userService;

    private GroupService groupService;

    private MessageService messageService;

    @PostMapping("/users")
    public User createUser(@RequestBody String username, @RequestBody String firstName, @RequestBody String lastName) {
        return userService.createUser(username, firstName, lastName);
    }

    @GetMapping("/users/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @PostMapping("/groups")
    public Group createGroup(@RequestParam String name) {
        return groupService.createGroup(name);
    }

    @GetMapping("/groups")
    public List<Group> getAllGroups() {
        return groupService.getAllGroups();
    }

    @PostMapping("/groups/{groupName}/users")
    public void addUserToGroup(@PathVariable String groupName, @RequestParam String username) {
        groupService.addUserToGroup(username, groupName);
    }

    @DeleteMapping("/groups/{groupName}/users")
    public void removeUserFromGroup(@PathVariable String groupName, @RequestParam String username) {
        groupService.removeUserFromGroup(username, groupName);
    }

    @PostMapping("/groups/{groupId}/messages")
    public void addMessage(@PathVariable Long groupId, @RequestBody String username, @RequestBody String text) {
        messageService.addMessage(groupId, username, text);
    }

    @GetMapping("/groups/{groupId}/messages")
    public List<Message> getAllMessages(@PathVariable Long groupId) {
        return messageService.getAllMessages(groupId);
    }
}


