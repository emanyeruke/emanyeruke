package com.cap10mycap10.ouathservice.service;


import com.cap10mycap10.ouathservice.dto.UserLogin;
import com.cap10mycap10.ouathservice.dto.UserLoginRequest;
import com.cap10mycap10.ouathservice.entity.ResultDTO;

public interface LoginService {
    ResultDTO userLogin(UserLogin userLogin) throws IllegalAccessException;

    ResultDTO refreshToken(String token, String username);

    ResultDTO clientLogin(UserLoginRequest request);
}
