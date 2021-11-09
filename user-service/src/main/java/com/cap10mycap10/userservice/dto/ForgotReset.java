package com.cap10mycap10.userservice.dto;

import lombok.Data;

@Data
public class ForgotReset {

    private String email;

    private String token;

    private String newPassword;

    private String matchingPassword;
}
