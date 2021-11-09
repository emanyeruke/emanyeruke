package zw.co.mynhaka.polad.events.payment;

import org.springframework.context.ApplicationEvent;
import zw.co.mynhaka.polad.domain.model.Invoice;
import zw.co.mynhaka.polad.domain.model.Payment;
import zw.co.mynhaka.polad.domain.model.PolicyHolder;


public class OnCreatePaymentEvent extends ApplicationEvent {

    private final Invoice invoice;
    private final Payment payment;
    private final PolicyHolder policyHolder;
    private final String action;


    public OnCreatePaymentEvent(Object source, Payment payment, PolicyHolder policyHolder, String action, Invoice invoice) {
        super(source);
        this.payment = payment;
        this.policyHolder = policyHolder;
        this.action = action;
        this.invoice = invoice;
    }

    public Payment getPayment() {
        return payment;
    }

    public PolicyHolder getPolicyHolder() {
        return policyHolder;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public String getAction() {
        return action;
    }
}
