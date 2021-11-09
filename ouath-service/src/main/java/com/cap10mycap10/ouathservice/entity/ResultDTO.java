package com.cap10mycap10.ouathservice.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResultDTO {

    private static final long serialVersionUID = 1064407930667435625L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("firstName")
    private String firstname;

    @JsonProperty("lastName")
    private String lastname;

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("scope")
    private String scope;

    @JsonProperty("expires_in")
    private String expireIn;

    private Long agentId;

    private Long clientId;

    private List<Role> roles = new ArrayList<>();

    @Override
    public String toString() {
        return "ResultDTO{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", tokenType='" + tokenType + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", scope='" + scope + '\'' +
                ", expireIn='" + expireIn + '\'' +
                '}';
    }
}


