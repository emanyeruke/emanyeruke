package zw.co.mynhaka.policyservice.domain.dto.cancelpolicy;

import lombok.Data;
import zw.co.mynhaka.policyservice.domain.enums.Reason;

import java.time.LocalDate;

@Data
public class CancelPolicyResultDTO {
    private Long id;

    private String policyNumber;

    private LocalDate effectiveDate;

    private String moreInformation;

    private Reason reason;

    private LocalDate submissionDate;
}
