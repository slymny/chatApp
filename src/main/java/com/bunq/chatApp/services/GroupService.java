package com.bunq.chatApp.services;

import com.bunq.chatApp.entities.Group;

import java.util.List;

public interface GroupService {
    Group createGroup(String name);
    Group getGroupByName(String name);
    List<Group> getAllGroups();
    void addUserToGroup(String username, String groupName);
    void removeUserFromGroup(String username, String groupName);
}

