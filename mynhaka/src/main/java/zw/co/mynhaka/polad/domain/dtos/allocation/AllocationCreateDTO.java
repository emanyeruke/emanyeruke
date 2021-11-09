package zw.co.mynhaka.polad.domain.dtos.allocation;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AllocationCreateDTO {

    private String policyNumber;

    private Long paymentId;

    private BigDecimal amount;


}
