package zw.co.mynhaka.polad.domain.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PremiumPaymentResultDTO {
    private Long id;

    private String policyNumber;

    private String paymentChannel;

    private String paymentReference;

    private LocalDate paymentDate;

    private BigDecimal amount;

    private String paymentStatus;
}
