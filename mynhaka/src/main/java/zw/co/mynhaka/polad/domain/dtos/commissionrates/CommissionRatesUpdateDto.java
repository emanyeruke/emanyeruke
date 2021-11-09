package zw.co.mynhaka.polad.domain.dtos.commissionrates;

import lombok.Data;
import zw.co.mynhaka.polad.domain.enums.PolicyType;

@Data
public class CommissionRatesUpdateDto {

    private Long id;

    private PolicyType policyType;

    private double tiedAgentCommissionRate;

    private double tiedUnitLeaderCommissionRate;

    private double executiveAgentCommissionRate;

    private double executiveUnitLeaderCommissionRate;

    private double parentAgentCommissionRate;

    private double parentUnitLeaderCommissionRate;

    private double upsellAgentCommissionRate;

    private double upsellManagerCommissionRate;

}
