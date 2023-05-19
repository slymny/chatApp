package com.bunq.chatApp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class GroupResponse {
    private Long id;
    private String name;
    private UserResponse creator;
    private Set<UserResponse> users;

}
