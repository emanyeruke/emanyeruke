package zw.co.mynhaka.policyservice.domain.dto.savingssurrender;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import zw.co.mynhaka.policyservice.domain.dto.bankingdetails.BankingDetailsCreateDTO;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class SavingsSurrenderCreateDTO {
    @NotNull
    private String policyNumber;

    @DateTimeFormat( pattern = "dd/MM/yyyy")
    private LocalDate effectiveDate;

    @NotNull
    private String identityDocument;

    private BankingDetailsCreateDTO bankingDetails;
}
