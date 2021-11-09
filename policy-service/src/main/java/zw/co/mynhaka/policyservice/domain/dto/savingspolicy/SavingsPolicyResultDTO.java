package zw.co.mynhaka.policyservice.domain.dto.savingspolicy;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class SavingsPolicyResultDTO {
    private Long id;

    private Long agentId;

    private Long savingsPlanId;

    private Long policyHolderId;

    private String policyNumber;

    private String paymentMethod;

    private String paymentFrequency;

    private String policyState;

    private LocalDate commencementDate;

    private String policyStatus;

    private String formLocation;

    private BigDecimal monthlyInvestmentPremium;

    private BigDecimal premiumWaiver;
}
