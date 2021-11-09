package zw.co.mynhaka.polad.domain.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SavingsSurrenderResultDTO {
    private Long savingsSurrenderId;

    private String policyNumber;

    private LocalDate effectiveDate;

    private String identityDocument;

    //private BankingDetailsResultDTO bankingDetails;

    private String surrenderStatus;
}
