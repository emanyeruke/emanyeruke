package com.cap10mycap10.userservice.service;


import com.cap10mycap10.userservice.model.PasswordResetToken;
import com.cap10mycap10.userservice.repository.PasswordResetTokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;

@Slf4j
@Service
@Transactional
public class UserSecurityService implements ISecurityUserService {


    private final PasswordResetTokenRepository passwordTokenRepository;

    public UserSecurityService(final PasswordResetTokenRepository passwordTokenRepository) {
        this.passwordTokenRepository = passwordTokenRepository;
    }

    @Override
    public String validatePasswordResetToken(String token) {

        final PasswordResetToken passToken = passwordTokenRepository.findByToken(token);

        log.info("Result: {} ", passToken.toString());
        return !isTokenFound(passToken) ? "invalidToken"
                : isTokenExpired(passToken) ? "expired"
                : null;
    }

    private boolean isTokenFound(PasswordResetToken passToken) {
        return passToken != null;
    }

    private boolean isTokenExpired(PasswordResetToken passToken) {
        final Calendar cal = Calendar.getInstance();
        return passToken.getExpiryDate().before(cal.getTime());
    }
}
