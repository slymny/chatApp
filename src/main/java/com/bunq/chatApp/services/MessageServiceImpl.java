package com.bunq.chatApp.services;

import com.bunq.chatApp.entities.Group;
import com.bunq.chatApp.entities.Message;
import com.bunq.chatApp.entities.User;
import com.bunq.chatApp.exception.NotFoundException;
import com.bunq.chatApp.repositories.GroupRepository;
import com.bunq.chatApp.repositories.MessageRepository;
import com.bunq.chatApp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {
    private MessageRepository messageRepository;

    private GroupRepository groupRepository;

    private UserRepository userRepository;

    @Override
    public Message addMessage(Long groupId, String username, String text) throws NotFoundException {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new NotFoundException("Grup is not found!"));
        User user = userRepository.findByUsername(username);
        if(user == null)
            throw new NotFoundException("User is not found!");
        if(!group.getUsers().contains(user))
            throw new IllegalArgumentException("User is not a member of the group!");
        Message message = new Message(group, user, text);
        messageRepository.save(message);
        return message;
    }

    @Override
    public List<Message> getAllMessages(Long groupId) {
        return messageRepository.findByGroupId(groupId);
    }
}

