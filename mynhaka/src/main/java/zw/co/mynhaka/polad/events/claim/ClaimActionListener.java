package zw.co.mynhaka.polad.events.claim;


import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.service.iface.EmailService;

@Component
public class ClaimActionListener implements ApplicationListener<OnClaimActionEvent> {

    private final EmailService emailService;

    public ClaimActionListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void onApplicationEvent(OnClaimActionEvent onClaimActionEvent) {
        // emailService.sendSimpleMessage();

    }
}
