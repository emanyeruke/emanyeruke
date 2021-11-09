package zw.co.mynhaka.polad.events.cancel;

import org.springframework.context.ApplicationEvent;

public class OnCancelPolicyEvent extends ApplicationEvent {

    private final String actionPerformed;
    private final String recipient;
    private final String message;


    public OnCancelPolicyEvent(Object source, String actionPerformed, String recipient, String message) {
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
