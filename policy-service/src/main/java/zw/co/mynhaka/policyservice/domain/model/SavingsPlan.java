package zw.co.mynhaka.policyservice.domain.model;

import lombok.*;
import zw.co.mynhaka.policyservice.domain.enums.PersonType;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(
        uniqueConstraints = @UniqueConstraint(
                name = "uk_name_investment_premium_person_type",
                columnNames = {
                        "name",
                        "monthlyInvestmentPremium",
                        "premiumWaiver",
                        "personType"
                }
        )
)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"product"})
@EqualsAndHashCode(callSuper = false, exclude = {"product", "clawbackPeriod"})
public class SavingsPlan extends AbstractAuditingEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal monthlyInvestmentPremium;

    private BigDecimal premiumWaiver;

    @Enumerated(EnumType.STRING)
    private PersonType personType;

    private int clawbackPeriod;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}
