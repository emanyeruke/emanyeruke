package zw.co.mynhaka.policyservice.domain.dto.savingssurrender;

import org.springframework.format.annotation.DateTimeFormat;
import zw.co.mynhaka.policyservice.domain.dto.bankingdetails.BankingDetailsCreateDTO;
import zw.co.mynhaka.policyservice.domain.enums.SurrenderStatus;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class SavingsSurrenderUpdateDTO {
    @NotNull
    private Long surrenderId;

    @NotNull
    private String policyNumber;

    @DateTimeFormat( pattern = "dd/MM/yyyy")
    private LocalDate effectiveDate;

    @NotNull
    private String identityDocument;

    private BankingDetailsCreateDTO bankingDetails;

    private SurrenderStatus surrenderStatus;
}
