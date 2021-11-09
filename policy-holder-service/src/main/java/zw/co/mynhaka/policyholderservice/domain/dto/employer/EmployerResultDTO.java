package zw.co.mynhaka.policyholderservice.domain.dto.employer;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EmployerResultDTO {
    private Long id;

    private String name;

    private String representative;

    private String email;

    private String contactNumber;

    private BigDecimal balance;
}
