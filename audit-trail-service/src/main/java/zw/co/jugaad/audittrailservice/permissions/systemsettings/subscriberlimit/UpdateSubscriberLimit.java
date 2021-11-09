package zw.co.jugaad.audittrailservice.permissions.systemsettings.subscriberlimit;


import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@PreAuthorize("hasAuthority('UPDATE_SUBSCRIBER_LIMIT')")
public @interface UpdateSubscriberLimit {
}
