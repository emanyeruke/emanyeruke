package com.cap10mycap10.userservice.service;


import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl {

    private static final String NOREPLY_ADDRESS = "noreply@mynhaka.co.zw";

    private final JavaMailSender emailSender;

    public EmailServiceImpl(final JavaMailSender emailSender
    ) {
        this.emailSender = emailSender;

    }

    public SimpleMailMessage sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        try {

            message.setFrom(NOREPLY_ADDRESS);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            new Thread(() -> emailSender.send(message)).start();

        } catch (MailException exception) {
            exception.printStackTrace();
        }
        return message;
    }
}
