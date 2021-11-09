package zw.co.mynhaka.policyservice.domain.dto.cancelpolicy;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import zw.co.mynhaka.policyservice.domain.enums.Reason;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class CancelPolicyCreateDTO {
    @NotNull
    private String policyNumber;

    @DateTimeFormat( pattern = "dd/MM/yyyy")
    private LocalDate effectiveDate;

    private String moreInformation;

    private Reason reason;

    private LocalDate submissionDate;
}
