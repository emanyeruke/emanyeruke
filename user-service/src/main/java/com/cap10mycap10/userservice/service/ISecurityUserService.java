package com.cap10mycap10.userservice.service;

public interface ISecurityUserService {

    String validatePasswordResetToken(String token);

}
