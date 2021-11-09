package zw.co.mynhaka.polad.domain.dtos.allocation;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AllocationResultDTO {

    private Long id;

    private Long paymentID;

    private String accidentPolicyNumber;

    private String funeralPolicyNumber;

    private String comprehensivePolicyNumber;

    private String savingsPolicyNumber;

    private BigDecimal amount;
}
