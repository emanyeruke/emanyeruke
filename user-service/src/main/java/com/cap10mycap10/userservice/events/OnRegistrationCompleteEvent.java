package com.cap10mycap10.userservice.events;


import com.cap10mycap10.userservice.model.User;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;


public class OnRegistrationCompleteEvent extends ApplicationEvent {
    User user;
    Locale locale;
    String appUrl;

    public OnRegistrationCompleteEvent(User user, Locale locale, String appUrl) {
        super(user);
        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
    }

    public User getUser() {
        return user;
    }

    public Locale getLocale() {
        return locale;
    }

    public String getAppUrl() {
        return appUrl;
    }


}
