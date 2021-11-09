package zw.co.mynhaka.policyservice.domain.dto.savingspartialwithdrawal;

import lombok.Data;
import zw.co.mynhaka.policyservice.domain.dto.bankingdetails.BankingDetailsResultDTO;
import zw.co.mynhaka.policyservice.domain.dto.mobilemoney.MobileMoneyDetailsResultDTO;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class SavingsPartialWithdrawalResultDTO {
    private Long id;

    private String savingsPolicyId;

    private String lifeAssured;

    private String purpose;

    private LocalDate inceptionDate;

    private String identityDocument;

    private BigDecimal amount;

    private BankingDetailsResultDTO bankingDetails;

    private MobileMoneyDetailsResultDTO mobileMoneyDetails;

    private String withdrawalStatus;
}
