package zw.co.mynhaka.polad.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.mynhaka.polad.domain.enums.PersonType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Table(
        uniqueConstraints = @UniqueConstraint(
                name = "uk_name_investment_premium_person_type",
                columnNames = {
                        "name",
                        "monthlyInvestmentPremium",
                        "premiumWaiverRate",
                        "personType"
                }
        )
)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SavingsProduct extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double monthlyInvestmentPremium;

    private double premiumWaiverRate;

    private double monthlyInterest;

    @Enumerated(EnumType.STRING)
    private PersonType personType;

    @ManyToOne(fetch = FetchType.LAZY)
    private Savings savings;

    private int clawbackPeriod;


}
