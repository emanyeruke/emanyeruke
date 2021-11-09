package zw.co.jugaad.audittrailservice.permissions.systemsettings.subscribervelocity;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@PreAuthorize("hasAuthority('CREATE_SUBSCRIBER_VELOCITY')")
public @interface CreateSubscriberVelocity {
}
