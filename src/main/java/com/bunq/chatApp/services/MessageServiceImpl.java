package com.bunq.chatApp.services;

import com.bunq.chatApp.entities.Group;
import com.bunq.chatApp.entities.Message;
import com.bunq.chatApp.entities.User;
import com.bunq.chatApp.repositories.GroupRepository;
import com.bunq.chatApp.repositories.MessageRepository;
import com.bunq.chatApp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {
    private MessageRepository messageRepository;

    private GroupRepository groupRepository;

    private UserRepository userRepository;

    @Override
    public void addMessage(Long groupId, String username, String text) {
        Group group = groupRepository.findById(groupId).orElseThrow();
        User user = userRepository.findByUsername(username);
        Message message = new Message(group, user, text);
        messageRepository.save(message);
    }

    @Override
    public List<Message> getAllMessages(Long groupId) {
        return messageRepository.findByGroupId(groupId);
    }
}

