package zw.co.mynhaka.polad.domain.dtos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class SavingsPartialWithdrawalCreateDTO {
    @NotNull
    private String policyNumber;

    private String lifeAssured;

    private String purpose;

    private LocalDate inceptionDate;

    private String identityDocument;

    private BigDecimal amount;

   // private BankingDetailsCreateDTO bankingDetails;

    //private MobileMoneyDetailsCreateDTO mobileMoneyDetails;
}