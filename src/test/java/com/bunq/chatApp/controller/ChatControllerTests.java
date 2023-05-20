package com.bunq.chatApp.controller;

import com.bunq.chatApp.dto.MessageRequest;
import com.bunq.chatApp.entities.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.bunq.chatApp.controllers.ChatController;
import com.bunq.chatApp.dto.GroupRequest;
import com.bunq.chatApp.entities.Group;
import com.bunq.chatApp.entities.User;
import com.bunq.chatApp.services.GroupService;
import com.bunq.chatApp.services.MessageService;
import com.bunq.chatApp.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ChatControllerTests {

    @Autowired
    private ChatController chatController;

    @MockBean
    private UserService userService;

    @MockBean
    private GroupService groupService;

    @MockBean
    private MessageService messageService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        openMocks(this);
    }

    @Test
    public void testCreateUser() throws Exception {
        User user = new User();
        user.setUsername("user1");
        user.setFirstname("user");
        user.setLastname("first");

        when(userService.createUser("user1", "user", "first")).thenReturn(user);

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("user1")))
                .andExpect(jsonPath("$.firstname", is("user")))
                .andExpect(jsonPath("$.lastname", is("first")));

        verify(userService, times(1)).createUser("user1", "user", "first");
    }

    @Test
    public void testGetUserByUsername() throws Exception {
        User user = new User();
        user.setUsername("user1");
        user.setFirstname("user");
        user.setLastname("first");

        when(userService.getUserByUsername("user1")).thenReturn(user);

        mockMvc.perform(get("/api/v1/users/{username}", "user1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("user1")))
                .andExpect(jsonPath("$.firstname", is("user")))
                .andExpect(jsonPath("$.lastname", is("first")));

        verify(userService, times(1)).getUserByUsername("user1");
    }

    @Test
    public void testCreateGroup() throws Exception {
        GroupRequest groupRequest = new GroupRequest();
        groupRequest.setCreatorId(1L);
        groupRequest.setName("Group1");

        User creator = new User();
        Group group = new Group();
        creator.setId(1L);
        group.setName("Group1");
        group.setCreator(creator);

        when(groupService.createGroup("Group1", 1L)).thenReturn(group);

        mockMvc.perform(post("/api/v1/groups")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(groupRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Group1")))
                .andExpect(jsonPath("$.creator.id", is(1)));

        verify(groupService, times(1)).createGroup("Group1", 1L);
    }

    @Test
    public void testGetAllGroups() throws Exception {
        Group group1 = new Group();
        group1.setName("Group 1");

        Group group2 = new Group();
        group2.setName("Group 2");

        List<Group> groups = Arrays.asList(group1, group2);

        when(groupService.getAllGroups()).thenReturn(groups);

        mockMvc.perform(get("/api/v1/groups"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Group 1")))
                .andExpect(jsonPath("$[1].name", is("Group 2")));

        verify(groupService, times(1)).getAllGroups();
    }

    @Test
    public void testAddUserToAGroup() throws Exception {
        User user = new User();
        user.setUsername("username1");
        when(groupService.addUserToGroup("username1", "Group1")).thenReturn(user);

        mockMvc.perform(post("/api/v1/groups/Group1/users")
                        .param("username", "username1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("username1")));

        verify(groupService, times(1)).addUserToGroup("username1", "Group1");
    }

    @Test
    public void testRemoveUserFromAGroup() throws Exception {
        mockMvc.perform(delete("/api/v1/groups/Group1/users")
                        .param("username", "username1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(groupService, times(1)).removeUserFromGroup("username1", "Group1");
    }

    @Test
    public void testAddMessageToAGroup() throws Exception {
        MessageRequest messageRequest = new MessageRequest("username1", "Hello, group!");

        User sender = new User();
        sender.setUsername("username1");
        Message message = new Message();
        message.setId(1L);
        message.setSender(sender);
        message.setText("Hello, group!");

        when(messageService.addMessage(1L, "username1", "Hello, group!")).thenReturn(message);

        mockMvc.perform(post("/api/v1/groups/1/messages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(messageRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.sender.username", is("username1")))
                .andExpect(jsonPath("$.text", is("Hello, group!")));

        verify(messageService, times(1)).addMessage(1L, "username1", "Hello, group!");
    }

    @Test
    public void testGetAllMessages() throws Exception {
        Group group = new Group();
        User sender = new User();
        List<Message> messages = Arrays.asList(
                new Message(group, sender, "Hello, group!"),
                new Message(group, sender, "Hi there!")
        );

        when(messageService.getAllMessages(1L)).thenReturn(messages);

        mockMvc.perform(get("/api/v1/groups/{groupId}/messages", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(messages.size())));

        verify(messageService, times(1)).getAllMessages(1L);
    }

    // Helper method to convert object to JSON string
    private static String asJsonString(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return objectMapper.writeValueAsString(object);
    }
}

