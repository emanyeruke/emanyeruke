package zw.co.mynhaka.polad.events.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.model.CommissionPayment;
import zw.co.mynhaka.polad.domain.model.Payment;
import zw.co.mynhaka.polad.domain.model.PolicyHolder;
import zw.co.mynhaka.polad.service.iface.*;

@Component
@RequiredArgsConstructor
public class CreatePaymentListener implements ApplicationListener<OnCreatePaymentEvent> {


    private final CommissionService commissionService;
    private final PolicyHolderService policyHolderService;
    private final PolicySavingsService policySavingsService;
    private final PolicyFuneralService policyFuneralService;
    private final PolicyComprehensiveService policyComprehensiveService;
    private final PolicyAccidentService policyAccidentService;
    private final AgentService agentService;
    private final ManagerService managerService;

    private final CommissionPaymentService commissionPaymentService;

    @Override
    public void onApplicationEvent(OnCreatePaymentEvent onCreatePaymentEvent) {
        PolicyHolder policyHolder = onCreatePaymentEvent.getPolicyHolder();
        Payment payment = onCreatePaymentEvent.getPayment();

       // commissionPaymentService.initiatePayment(comm);

        /*Agent firstAgent = onCreatePaymentEvent.getPolicyHolder().getAgent();
        Manager firstManager = onCreatePaymentEvent.getPolicyHolder().getAgent().getManager();

        HashSet<PolicyDocument> policies = new HashSet<>();

        for (InvoiceItem invoiceItem : onCreatePaymentEvent.getInvoice().getInvoiceItemSet()
        ) {
            policies.add(new PolicyDocument(invoiceItem.getPolicyNumber(), invoiceItem.getPolicyType().toString()));
        }

        for (PolicyDocument p : policies
        ) {
            System.out.println("PolicyNumber: " + p.policyNumber + ", PolicyType:  " + p.policyType);
        }*/
    }

}
