package com.cap10mycap10.userservice.dto;

import lombok.Data;

@Data
public class UserSearchResponse {

    private boolean userFound;

    private UserResponse user;

    public UserSearchResponse(boolean userFound, UserResponse user) {
        this.userFound = userFound;
        this.user = user;
    }

    public UserSearchResponse(boolean userFound) {
        this.userFound = userFound;
    }
}
