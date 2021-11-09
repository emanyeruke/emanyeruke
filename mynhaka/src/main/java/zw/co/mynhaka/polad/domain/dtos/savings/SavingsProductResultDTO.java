package zw.co.mynhaka.polad.domain.dtos.savings;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SavingsProductResultDTO {

    private Long id;

    private String name;

    private double monthlyInvestmentPremium;

    private double premiumWaiverRate;

    private String personType;

    private Long savingsId;

    private int clawbackPeriod;
}
