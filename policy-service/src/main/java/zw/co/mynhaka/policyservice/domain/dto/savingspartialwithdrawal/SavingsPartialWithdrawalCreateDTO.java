package zw.co.mynhaka.policyservice.domain.dto.savingspartialwithdrawal;

import lombok.Data;
import zw.co.mynhaka.policyservice.domain.dto.bankingdetails.BankingDetailsCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.mobilemoney.MobileMoneyDetailsCreateDTO;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class SavingsPartialWithdrawalCreateDTO {
    private String policyNumber;

    private String lifeAssured;

    private String purpose;

    private LocalDate inceptionDate;

    private String identityDocument;

    private BigDecimal amount;

    private BankingDetailsCreateDTO bankingDetails;

    private MobileMoneyDetailsCreateDTO mobileMoneyDetails;
}
