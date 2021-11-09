package zw.co.mynhaka.paymentservice.domain.dto.payment;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PremiumPaymentResultDTO {
    private Long id;

    private String policyNumber;

    private String paymentChannel;

    private String paymentReference;

    @DateTimeFormat( pattern = "dd/MM/yyyy")
    private LocalDate paymentDate;

    @DecimalMin("0.00")
    private BigDecimal amount;

    String paymentStatus;
}
