package com.bunq.chatApp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Getter
@Setter
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Group group;

    @ManyToOne
    private User user;

    private String text;

    private LocalDateTime createdAt;

    public Message(Group group, User user, String text) {
        this.group = group;
        this.user = user;
        this.text = text;
        this.createdAt = LocalDateTime.now();
    }
}

