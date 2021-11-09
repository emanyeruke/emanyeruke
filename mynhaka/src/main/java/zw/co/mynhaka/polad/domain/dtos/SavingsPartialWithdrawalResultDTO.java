package zw.co.mynhaka.polad.domain.dtos;

import lombok.Data;

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

   // private BankingDetailsResultDTO bankingDetails;

   // private MobileMoneyDetailsResultDTO mobileMoneyDetails;

    private String withdrawalStatus;
}

