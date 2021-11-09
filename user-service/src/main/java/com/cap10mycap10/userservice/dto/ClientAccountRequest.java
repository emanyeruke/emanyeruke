package com.cap10mycap10.userservice.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class ClientAccountRequest {

    @Size(min = 1, message = "{Size.userDto.firstName}")
    private String firstName;

    @Size(min = 1, message = "{Size.userDto.lastName}")
    private String lastName;

    @Size(min = 5, max = 50)
    private String username;

    private String password;

    @Size(min = 1)
    private String matchingPassword;

    @Size(min = 1, message = "{Size.userDto.email}")
    private String email;

    private Long clientId;

}
