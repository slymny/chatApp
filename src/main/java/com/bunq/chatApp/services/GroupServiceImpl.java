package com.bunq.chatApp.services;

import com.bunq.chatApp.entities.Group;
import com.bunq.chatApp.entities.User;
import com.bunq.chatApp.repositories.GroupRepository;
import com.bunq.chatApp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GroupServiceImpl implements GroupService {
    private GroupRepository groupRepository;

    private UserRepository userRepository;

    @Override
    public Group createGroup(String name) {
        Group group = new Group();
        group.setName(name);
        return groupRepository.save(group);
    }

    @Override
    public Group getGroupByName(String name) {
        return groupRepository.findByName(name);
    }

    @Override
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    @Override
    public void addUserToGroup(String username, String groupName) {
        User user = userRepository.findByUsername(username);
        Group group = groupRepository.findByName(groupName);
        group.getUsers().add(user);
        groupRepository.save(group);
    }

    @Override
    public void removeUserFromGroup(String username, String groupName) {
        User user = userRepository.findByUsername(username);
        Group group = groupRepository.findByName(groupName);
        group.getUsers().remove(user);
        groupRepository.save(group);
    }
}

