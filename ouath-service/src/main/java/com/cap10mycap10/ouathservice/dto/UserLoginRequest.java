package com.cap10mycap10.ouathservice.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserLoginRequest extends UserLogin {

    private String clientSecret;

    private String clientId;

}
