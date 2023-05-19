package com.bunq.chatApp.services;

import com.bunq.chatApp.entities.Group;
import com.bunq.chatApp.entities.User;
import com.bunq.chatApp.exception.NotFoundException;

import java.util.List;

public interface GroupService {

    Group createGroup(String name, Long creatorId) throws NotFoundException;

    Group getGroupByName(String name);
    List<Group> getAllGroups();

    User addUserToGroup(String username, String groupName) throws NotFoundException;

    void removeUserFromGroup(String username, String groupName) throws NotFoundException;
    List<User> getUsersOfGroup(Long groupId) throws NotFoundException;
}

