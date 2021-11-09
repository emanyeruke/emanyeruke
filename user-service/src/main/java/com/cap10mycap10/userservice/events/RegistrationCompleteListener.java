package com.cap10mycap10.userservice.events;


import com.cap10mycap10.userservice.model.User;
import com.cap10mycap10.userservice.service.EmailServiceImpl;
import com.cap10mycap10.userservice.service.UserService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class RegistrationCompleteListener implements ApplicationListener<OnRegistrationCompleteEvent> {


    private final UserService service;

    private final MessageSource messages;

    private final EmailServiceImpl emailService;

    public RegistrationCompleteListener(final UserService service,
                                        final MessageSource messages,
                                        final EmailServiceImpl emailService) {
        this.service = service;
        this.messages = messages;
        this.emailService = emailService;
    }

    // API

    @Override
    public void onApplicationEvent(final OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(final OnRegistrationCompleteEvent event) {

        final User user = event.getUser();
        final String token = UUID.randomUUID().toString();
        service.createVerificationTokenForUser(user, token);
        final SimpleMailMessage email = constructEmailMessage(event, user, token);

    }


    private SimpleMailMessage constructEmailMessage(final OnRegistrationCompleteEvent event, final User user, final String token) {
        final String recipientAddress = user.getEmail();
        final String subject = "Registration Confirmation";
        final String confirmationUrl = event.getAppUrl() + "/registrationConfirm.html?token=" + token;
        final String message = messages.getMessage("message.regSuccLink", null,
                "You registered successfully. Once the system administrator has approved your registration," +
                        " you will be able to login.", event.getLocale());
        final SimpleMailMessage email = emailService.sendSimpleMessage(recipientAddress, subject, message + " \r\n" + confirmationUrl);
        return email;
    }

}
