package zw.co.mynhaka.paymentservice.domain.dto.policy;

import lombok.Data;
import zw.co.mynhaka.paymentservice.domain.enums.PolicyState;
import zw.co.mynhaka.paymentservice.domain.enums.PolicyStatus;
import zw.co.mynhaka.paymentservice.domain.enums.PolicyType;

import java.time.LocalDate;

@Data
public class PolicyResultDTO {
    private Long id;

    private String policyNumber;

    private Long agentId;

    private Long policyHolderId;

    private String paymentMethod;

    private String paymentFrequency;

    private LocalDate nextInvoicingDate;

    private LocalDate commencementDate;

    private PolicyType policyType;

    private PolicyStatus policyStatus;

    private PolicyState policyState;

    private String applicationForm_url;
}
