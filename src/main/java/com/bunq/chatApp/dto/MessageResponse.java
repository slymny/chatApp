package com.bunq.chatApp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class MessageResponse {
    private Long id;
    private UserResponse sender;
    private String text;
    private LocalDateTime createdAt;
}
