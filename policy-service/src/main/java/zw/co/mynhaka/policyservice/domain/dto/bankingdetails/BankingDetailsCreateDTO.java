package zw.co.mynhaka.policyservice.domain.dto.bankingdetails;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BankingDetailsCreateDTO {
    @NotNull
    private String bankName;

    @NotNull
    private String branch;

    @NotNull
    private String accountName;

    @NotNull
    private String accountNumber;
}
