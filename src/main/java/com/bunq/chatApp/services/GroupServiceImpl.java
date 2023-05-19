package com.bunq.chatApp.services;

import com.bunq.chatApp.entities.Group;
import com.bunq.chatApp.entities.User;
import com.bunq.chatApp.exception.NotFoundException;
import com.bunq.chatApp.repositories.GroupRepository;
import com.bunq.chatApp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
public class GroupServiceImpl implements GroupService {
    private GroupRepository groupRepository;

    private UserRepository userRepository;

    @Override
    public Group createGroup(String name, Long creatorId) throws NotFoundException {
        User creator = userRepository.findById(creatorId).orElseThrow(() -> new NotFoundException("User is not found!"));
        Group group = new Group(name, new HashSet<>(), creator);
        group.getUsers().add(creator);
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
    public User addUserToGroup(String username, String groupName) throws NotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null)
            throw new NotFoundException("User is not found!");
        Group group = groupRepository.findByName(groupName);
        if(group == null)
            throw new NotFoundException("Group is not found!");
        if(group.getUsers().contains(user))
            throw new IllegalArgumentException("User is already a member!");
        group.getUsers().add(user);
        groupRepository.save(group);
        return user;
    }

    @Override
    public void removeUserFromGroup(String username, String groupName) throws NotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null)
            throw new NotFoundException("User is not found!");
        Group group = groupRepository.findByName(groupName);
        if(group == null)
            throw new NotFoundException("Group is not found!");
        if(!group.getUsers().contains(user))
            throw new IllegalArgumentException("User is not a member!");
        group.getUsers().remove(user);
        groupRepository.save(group);
    }

    @Override
    public List<User> getUsersOfGroup(Long groupId) throws NotFoundException {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new NotFoundException("Group is not found!"));
        return group.getUsers().stream().toList();
    }
}

