package zw.co.mynhaka.polad.domain.dtos;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BankingDetailsCreateDTO {
    private String bankName;

    private String branch;

    private String accountName;

    private String accountNumber;
}
