package com.bunq.chatApp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "groups")
@Getter
@Setter
@NoArgsConstructor
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private User creator;

    @ManyToMany
    private Set<User> users;

    private LocalDateTime createdAt;

    public Group(String name, Set<User> users, User creator) {
        this.name = name;
        this.users = users;
        this.createdAt = LocalDateTime.now();
        this.creator = creator;
    }


}



