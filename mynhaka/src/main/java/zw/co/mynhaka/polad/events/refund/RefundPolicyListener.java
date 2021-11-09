package zw.co.mynhaka.polad.events.refund;


import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.service.iface.EmailService;

@Component
public class RefundPolicyListener implements ApplicationListener<OnRefundPolicyEvent> {

    private final EmailService emailService;

    public RefundPolicyListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void onApplicationEvent(OnRefundPolicyEvent onRefundPolicyEvent) {
        // emailService.sendSimpleMessage();

    }
}
