package zw.co.mynhaka.polad.domain.dtos.accident;


import lombok.Data;
import zw.co.mynhaka.polad.domain.enums.PaymentFrequency;
import zw.co.mynhaka.polad.domain.enums.PaymentMethod;

@Data
public class PolicyAccidentUpdateCreateDto {

    private Long policyId;

    private PaymentMethod paymentMethod;

    private PaymentFrequency paymentFrequency;


}
