package zw.co.mynhaka.polad.domain.dtos;

import lombok.Data;

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

    private String policyType;

    private String policyStatus;

    private String policyState;

    private String applicationForm_url;
}
