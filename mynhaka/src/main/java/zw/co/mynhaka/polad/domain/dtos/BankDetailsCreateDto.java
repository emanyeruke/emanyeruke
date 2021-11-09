package zw.co.mynhaka.polad.domain.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BankDetailsCreateDto {
    private String bankName;
    private String branch;
    private String accountNumber;
    private LocalDate debitDate;
}
