package zw.co.mynhaka.polad.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.mynhaka.polad.domain.enums.MaturityStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class SavingsMaturity extends AbstractAuditingEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private PolicySavings policySavings;

    private LocalDate effectiveDate;

    private String identityDocument;

    //private BankingDetails bankingDetails;

    private BigDecimal maturityValue;

   /* private double totalPayout;

    private MaturityStatus maturityStatus = MaturityStatus.NEW;


    //method to calculate total payout
    public void getTotalValue(){
        for (totalPayout=policySavings.getSavingsProduct().getMonthlyInvestmentPremium();
             totalPayout>=policySavings.getSavingsProduct().getMonthlyInvestmentPremium();totalPayout++)

            totalPayout += policySavings.getSavingsProduct().getMonthlyInvestmentPremium() * policySavings.getSavingsProduct().getMonthlyInterest();
    }

    */
}
