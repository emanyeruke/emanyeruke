package zw.co.mynhaka.policyservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.mynhaka.policyservice.domain.enums.WithdrawalStatus;

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

    private String lifeAssured;

    private String purpose;

    private LocalDate inceptionDate;

    private String identityDocument;

    private BigDecimal amount;

    private BankingDetails bankingDetails;

    private MobileMoneyDetails mobileMoneyDetails;

    @ManyToOne
    private SavingsPolicy savingsPolicy;

    private WithdrawalStatus withdrawalStatus = WithdrawalStatus.NEW;
}
