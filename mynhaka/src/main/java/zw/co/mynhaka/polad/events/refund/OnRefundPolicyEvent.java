package zw.co.mynhaka.polad.events.refund;

import org.springframework.context.ApplicationEvent;

public class OnRefundPolicyEvent extends ApplicationEvent {

    private final String actionPerformed;
    private final String recipient;
    private final String message;


    public OnRefundPolicyEvent(Object source, String actionPerformed, String recipient, String message) {
        super(source);
        this.actionPerformed = actionPerformed;
        this.recipient = recipient;
        this.message = message;
    }

    public String getActionPerformed() {
        return actionPerformed;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessage() {
        return message;
    }
}
