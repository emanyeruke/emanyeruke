package com.cap10mycap10.userservice.config;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderComponent {

    private final PasswordEncoder passwordEncoder;

    public PasswordEncoderComponent(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }
}
