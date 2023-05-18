package com.bunq.chatApp.services;

import com.bunq.chatApp.entities.Message;

import java.util.List;

public interface MessageService {
    void addMessage(Long groupId, String username, String text);
    List<Message> getAllMessages(Long groupId);
}

