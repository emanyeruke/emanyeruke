package zw.co.mynhaka.polad.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.mynhaka.polad.domain.enums.PolicyType;
import zw.co.mynhaka.polad.service.exception.BusinessValidationException;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(
        uniqueConstraints = @UniqueConstraint(
                name = "policy_type",
                columnNames = {
                        "policyType"
                }
        )
)
public class Commission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
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
