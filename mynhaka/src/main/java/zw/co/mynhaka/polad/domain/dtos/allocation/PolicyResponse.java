package zw.co.mynhaka.polad.domain.dtos.allocation;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PolicyResponse {
    String policyNumber;
    BigDecimal totalPremium;
}
