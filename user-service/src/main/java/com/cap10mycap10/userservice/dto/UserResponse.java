package com.cap10mycap10.userservice.dto;


import com.cap10mycap10.userservice.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
public class UserResponse {

    private Long id;

    private String firstname;

    private String lastname;

    private String username;

    private String email;

    private String enabled;

    private Long agentId;

    private Long clientId;

    private List<Role> roles = new ArrayList<>();

    public UserResponse() {

    }
}
