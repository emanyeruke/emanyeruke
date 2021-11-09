package zw.co.mynhaka.polad.domain.dtos.claim;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import zw.co.mynhaka.polad.domain.enums.Reason;

import java.time.LocalDate;

@Data
public class CancelPolicyCreateDTO {
    private String policyNumber;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate effectiveDate;

    private String moreInformation;

    private Reason reason;

    private LocalDate submissionDate;
}
