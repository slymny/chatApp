package com.bunq.chatApp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Getter
@Setter
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne
    private User sender;

    private String text;

    private LocalDateTime createdAt;

    @Autowired
    public Message(Group group, User sender, String text) {
        this.group = group;
        this.sender = sender;
        this.text = text;
        this.createdAt = LocalDateTime.now();
    }
}

