package com.bunq.chatApp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GroupRequest {
    private String name;
    private Long creatorId;
}
