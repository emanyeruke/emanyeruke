package com.cap10mycap10.userservice.mapper;


import com.cap10mycap10.userservice.dto.UserResponse;
import com.cap10mycap10.userservice.model.User;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
public class UserToUserResponse implements Converter<User, UserResponse> {


    @Override
    public UserResponse convert(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setFirstname(user.getFirstName());
        userResponse.setLastname(user.getLastName());

        userResponse.setEmail(user.getEmail());
        userResponse.setUsername(user.getUsername());
        userResponse.setAgentId(user.getAgentId());
        userResponse.setClientId(user.getClientId());
        if (user.isEnabled()) {
            userResponse.setEnabled("Active");
        } else {
            userResponse.setEnabled("Inactive");
        }
        userResponse.setRoles(user.getRoles().stream().distinct().collect(Collectors.toList()));
        return userResponse;
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return null;
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return null;
    }
}
