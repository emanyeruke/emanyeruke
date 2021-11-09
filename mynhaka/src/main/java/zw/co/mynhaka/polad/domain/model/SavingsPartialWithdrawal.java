package zw.co.mynhaka.polad.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.mynhaka.polad.domain.enums.WithdrawalStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class SavingsPartialWithdrawal extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String policyNumber;

    private String lifeAssured;

    private String purpose;

    private LocalDate inceptionDate;

    private String identityDocument;

    private double amount; //total

    private double percentagePenalty;

    private double penalty;

    private double totalPayout;

    private double withdrawalAmount;

    private double balance; //to be removed if redundant


    //private BankingDetails bankingDetails;

   // private MobileMoneyDetails mobileMoneyDetails;

    @ManyToOne
    private PolicySavings policySavings;

    private WithdrawalStatus withdrawalStatus = WithdrawalStatus.NEW;

    //method to calculate penalty
    public double getPenalty(double percentagePenalty,double totalPayout){
        penalty = percentagePenalty*totalPayout;
        return penalty;
    }


    //method to calculate amount
    public void getTotalValue(){
        for (amount=policySavings.getSavingsProduct().getMonthlyInvestmentPremium();
             amount>=policySavings.getSavingsProduct().getMonthlyInvestmentPremium();amount++)

            amount += policySavings.getSavingsProduct().getMonthlyInvestmentPremium() * policySavings.getSavingsProduct().getMonthlyInterest();
    }

    //method to get payout value for client
    public double getPayOut(double amount, double penalty){
        totalPayout = amount - penalty;
        return totalPayout;
    }

    //calculate withdrawal amount
    public double getWithdrawal(double totalPayout){
        withdrawalAmount = 0.75*totalPayout;
        return withdrawalAmount;
    }
        //get balance
    public double retrieveBalance(double totalPayout, double withdrawalAmount){
        balance = totalPayout-withdrawalAmount;
        return  balance;
    }
}

