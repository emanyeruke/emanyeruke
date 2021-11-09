package zw.co.mynhaka.polad.domain.dtos.agent;


import lombok.Data;
import zw.co.mynhaka.polad.domain.enums.PolicyType;

import java.math.BigDecimal;

@Data
public class CommissionUpdateDTO {

    private Long commissionId;

    private int month;

    private double tiedAgentCommissionRate;

    private double tiedUnitLeaderCommissionRate;

    private double executiveAgentCommissionRate;

    private double executiveUnitLeaderCommissionRate;

    private double parentAgentCommissionRate;

    private double parentUnitLeaderCommissionRate;

    private double upsellAgent;

    private double upsellManager;

    private PolicyType policyType;
}
