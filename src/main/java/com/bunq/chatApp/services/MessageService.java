package com.bunq.chatApp.services;

import com.bunq.chatApp.entities.Message;
import com.bunq.chatApp.exception.NotFoundException;

import java.util.List;

public interface MessageService {
    Message addMessage(Long groupId, String username, String text) throws NotFoundException;
    List<Message> getAllMessages(Long groupId);
}

