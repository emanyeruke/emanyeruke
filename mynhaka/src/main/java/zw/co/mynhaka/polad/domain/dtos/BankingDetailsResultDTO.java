package zw.co.mynhaka.polad.domain.dtos;

import lombok.Data;

@Data
public class BankingDetailsResultDTO {
    private String bankName;

    private String branch;

    private String accountName;

    private String accountNumber;
}
