package com.cap10mycap10.userservice.dto;

import lombok.Data;

@Data
public class EnableAdminUserRequest {

    private Long userId;

    private String roleName;

    private boolean enabled;
}
