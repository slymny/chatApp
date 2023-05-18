package com.bunq.chatApp.services;

import com.bunq.chatApp.entities.Group;
import com.bunq.chatApp.entities.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class GroupServiceImplTest {
    @Autowired
    private GroupService groupService;

    @Autowired
    private UserService userService;

    @Test
    public void testCreateGroup() {
        Group group = groupService.createGroup("group1");
        assertNotNull(group.getId());
        assertEquals("group1", group.getName());
    }

    @Test
    public void testGetAllGroups() {
        Group group1 = groupService.createGroup("group1");
        Group group2 = groupService.createGroup("group2");
        List<Group> groups = groupService.getAllGroups();
        assertTrue(groups.contains(group1));
        assertTrue(groups.contains(group2));
    }

    @Test
    public void testAddUserToGroup() {
        User user1 = userService.createUser("user1", "user", "first");
        Group group1 = groupService.createGroup("group1");
        groupService.addUserToGroup(user1.getUsername(), group1.getName());
        assertTrue(group1.getUsers().contains(user1));
    }

    @Test
    public void testRemoveUserFromGroup() {
        User user1 = userService.createUser("user1", "user", "first");
        Group group1 = groupService.createGroup("group1");
        groupService.addUserToGroup(user1.getUsername(), group1.getName());
        groupService.removeUserFromGroup(user1.getUsername(), group1.getName());
        assertFalse(group1.getUsers().contains(user1));
    }
}

