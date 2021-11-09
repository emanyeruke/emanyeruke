package zw.co.mynhaka.policyservice.domain.dto.savingssurrender;

import lombok.Data;
import zw.co.mynhaka.policyservice.domain.dto.bankingdetails.BankingDetailsResultDTO;
import zw.co.mynhaka.policyservice.domain.enums.SurrenderStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class SavingsSurrenderResultDTO {
    private Long savingsSurrenderId;

    private String policyNumber;

    private LocalDate effectiveDate;

    private String identityDocument;

    private SurrenderStatus surrenderStatus = SurrenderStatus.NEW;

    private BankingDetailsResultDTO bankingDetails;

    private BigDecimal surrenderValue;
}
