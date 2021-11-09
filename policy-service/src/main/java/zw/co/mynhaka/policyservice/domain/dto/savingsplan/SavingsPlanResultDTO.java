package zw.co.mynhaka.policyservice.domain.dto.savingsplan;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SavingsPlanResultDTO {
    private Long id;

    private String name;

    private BigDecimal monthlyInvestmentPremium;

    private BigDecimal premiumWaiver;

    private String personType;

    private Long productId;

    private int clawbackPeriod;
}
