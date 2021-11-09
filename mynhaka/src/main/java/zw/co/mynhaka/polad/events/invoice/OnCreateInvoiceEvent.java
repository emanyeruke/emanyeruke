package zw.co.mynhaka.polad.events.invoice;

import org.springframework.context.ApplicationEvent;
import zw.co.mynhaka.polad.domain.model.Invoice;

public class OnCreateInvoiceEvent extends ApplicationEvent {

    private final Invoice invoice;
    private final String emailAddress;
    private String attachment;

    public OnCreateInvoiceEvent(Object source, Invoice invoice, String emailAddress, String attachment) {
        super(source);
        this.invoice = invoice;
        this.emailAddress = emailAddress;
        this.attachment = attachment;
    }

    public OnCreateInvoiceEvent(Object source, Invoice invoice, String emailAddress) {
        super(source);
        this.invoice = invoice;
        this.emailAddress = emailAddress;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getAttachment() {
        return attachment;
    }
}
