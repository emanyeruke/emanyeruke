package zw.co.mynhaka.policyservice.domain.dto.savingspolicy;

import lombok.Data;
import zw.co.mynhaka.policyservice.domain.enums.PaymentFrequency;
import zw.co.mynhaka.policyservice.domain.enums.PaymentMethod;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Data
public class SavingsPolicyCreateDTO {
    private Long agentId;

    private Long policyHolderId;

    private Long savingsPlanId;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private PaymentFrequency paymentFrequency;

    private String formLocation;

    private LocalDate commencementDate;
}
