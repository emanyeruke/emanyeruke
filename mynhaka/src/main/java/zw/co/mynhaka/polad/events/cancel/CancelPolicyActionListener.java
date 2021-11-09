package zw.co.mynhaka.polad.events.cancel;


import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.service.iface.EmailService;

@Component
public class CancelPolicyActionListener implements ApplicationListener<OnCancelPolicyEvent> {

    private final EmailService emailService;

    public CancelPolicyActionListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void onApplicationEvent(OnCancelPolicyEvent onCancelPolicyEvent) {
        // emailService.sendSimpleMessage();

    }
}
