package zw.co.mynhaka.polad.domain.dtos;

import lombok.Data;
import zw.co.mynhaka.polad.domain.enums.WithdrawalStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class SavingsPartialWithdrawalUpdateDTO {
    private Long id;

    private String policyNumber;

    private String lifeAssured;

    private String purpose;

    private LocalDate inceptionDate;

    private String identityDocument;

    private BigDecimal amount;

    //private BankingDetailsCreateDTO bankingDetails;

   // private MobileMoneyDetailsCreateDTO mobileMoneyDetails;

    private WithdrawalStatus withdrawalStatus;
}
