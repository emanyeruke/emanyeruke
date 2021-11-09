package zw.co.mynhaka.policyservice.domain.dto.bankingdetails;

import lombok.Data;

@Data
public class BankingDetailsResultDTO {
    private String bankName;

    private String branch;

    private String accountName;

    private String accountNumber;
}
