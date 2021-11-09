package com.cap10mycap10.userservice.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class EditTellerRequest {

    @Size(min = 1, message = "{Size.userDto.firstName}")
    private String firstName;

    @Size(min = 1, message = "{Size.userDto.lastName}")
    private String lastName;

    @Size(min = 5, max = 50)
    private String username;

    @Size(min = 1, message = "{Size.userDto.email}")
    private String email;

    private Long roleId;
}
