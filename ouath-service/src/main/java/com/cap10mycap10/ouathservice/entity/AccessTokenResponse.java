package com.cap10mycap10.ouathservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AccessTokenResponse {

    @JsonProperty(value = "access_token")
    private String accessToken;

    @JsonProperty(value = "token_type")
    private String tokenType;

    @JsonProperty(value = "refresh_token")
    private String refreshToken;

    @JsonProperty(value = "expires_in")
    private String expiresIn;

    @JsonProperty(value = "scope")
    private String scope;
}
