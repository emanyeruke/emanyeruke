package zw.co.mynhaka.polad.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.mynhaka.polad.domain.enums.SurrenderStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class SavingsSurrender extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private PolicySavings policySavings;

    private LocalDate effectiveDate;

    private String identityDocument;

    //private BankingDetails bankingDetails;

    private BigDecimal surrenderValue;//initial payout

    /*private double percentagePenalty;

    private double penalty;

    private double totalPayout;

    private SurrenderStatus surrenderStatus = SurrenderStatus.NEW;

    //method to calculate penalty
    public double getPenalty(double percentagePenalty,double totalPayout){
        penalty = percentagePenalty*totalPayout;
        return penalty;
    }

    //method to calculate surrenderValue
    public void getTotalValue(){
       for (surrenderValue=policySavings.getSavingsProduct().getMonthlyInvestmentPremium();
                    surrenderValue>=policySavings.getSavingsProduct().getMonthlyInvestmentPremium();surrenderValue++)

           surrenderValue += policySavings.getSavingsProduct().getMonthlyInvestmentPremium() * policySavings.getSavingsProduct().getMonthlyInterest();
    }

    //method to get payout value for client
    public double getPayOut(double surrenderValue, double penalty){
        totalPayout = surrenderValue - penalty;
        return totalPayout;
    }
    //TODO: Add Date validation for age of policy to be from 12-60 months for surrender

     */
}
