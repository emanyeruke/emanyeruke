package zw.co.mynhaka.polad.domain.dtos;

import lombok.Data;
import zw.co.mynhaka.polad.domain.enums.SurrenderStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class SavingsSurrenderUpdateDTO {
    private Long id;

    private String policyNumber;

    private LocalDate effectiveDate;

    private String identityDocument;

   // private BankingDetailsResultDTO bankingDetails;

    private BigDecimal surrenderValue;

    private SurrenderStatus surrenderStatus;
}
