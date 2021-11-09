package com.cap10mycap10.userservice.dto;

import lombok.Data;

@Data
public class ChangePasswordRequest {

    private String oldPassword;

    private String matchingPassword;

    private String newPassword;
}
