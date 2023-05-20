package com.bunq.chatApp.controllers;

import com.bunq.chatApp.dto.*;
import com.bunq.chatApp.entities.Group;
import com.bunq.chatApp.entities.User;
import com.bunq.chatApp.exception.NotFoundException;
import com.bunq.chatApp.services.GroupService;
import com.bunq.chatApp.services.MessageService;
import com.bunq.chatApp.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ChatController extends BaseController {
    private final UserService userService;

    private final GroupService groupService;

    private final MessageService messageService;

    public ChatController(ModelMapper modelMapper, UserService userService, GroupService groupService, MessageService messageService) {
        super(modelMapper);
        this.userService = userService;
        this.groupService = groupService;
        this.messageService = messageService;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user.getUsername(), user.getFirstname(), user.getLastname()));
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @GetMapping("/users")
    public ResponseEntity<User> getUserByUserId(@RequestParam Long userId) throws NotFoundException {
        return ResponseEntity.ok(userService.getUserByUserId(userId));
    }

    @PostMapping("/groups")
    public ResponseEntity<Group> createGroup(@RequestBody GroupRequest groupRequest) throws NotFoundException {
        return ResponseEntity.ok(groupService.createGroup(groupRequest.getName(), groupRequest.getCreatorId()));
    }

    @GetMapping("/groups")
    public ResponseEntity<List<GroupResponse>> getAllGroups() {
        List<GroupResponse> groupResponses = mapAll(groupService.getAllGroups(), GroupResponse.class);
        return ResponseEntity.ok(groupResponses);
    }

    @GetMapping("/groups/{groupName}")
    public ResponseEntity<Group> getOneGroup(@PathVariable String groupName) {
        return ResponseEntity.ok(groupService.getGroupByName(groupName));
    }

    @PostMapping("/groups/{groupName}/users")
    public ResponseEntity<User> addUserToGroup(@PathVariable String groupName, @RequestParam String username) throws NotFoundException {
        return ResponseEntity.ok(groupService.addUserToGroup( username, groupName));
    }

    @DeleteMapping("/groups/{groupName}/users")
    public ResponseEntity<Boolean> removeUserFromGroup(@PathVariable String groupName, @RequestParam String username) throws NotFoundException {
        groupService.removeUserFromGroup(username, groupName);
        return ResponseEntity.ok(true);
    }

    @GetMapping("/groups/{groupId}/users")
    public ResponseEntity<List<UserResponse>> getUsersOfGroup(@PathVariable Long groupId) throws NotFoundException {
        List<UserResponse> userResponses = mapAll(groupService.getUsersOfGroup(groupId), UserResponse.class);
        return ResponseEntity.ok(userResponses);
    }

    @PostMapping("/groups/{groupId}/messages")
    public ResponseEntity<MessageResponse> addMessage(@PathVariable Long groupId, @RequestBody MessageRequest message) throws NotFoundException {
        MessageResponse messageResponse = map(messageService.addMessage(groupId, message.getSender(), message.getText()), MessageResponse.class);
        return ResponseEntity.ok(messageResponse);
    }

    @GetMapping("/groups/{groupId}/messages")
    public ResponseEntity<List<MessageResponse>> getAllMessages(@PathVariable Long groupId) {
        List<MessageResponse> allMessageResponse = mapAll(messageService.getAllMessages(groupId), MessageResponse.class);
        return ResponseEntity.ok(allMessageResponse);
    }
}


