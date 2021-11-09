package zw.co.mynhaka.polad.domain.dtos.payment;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import zw.co.mynhaka.polad.domain.enums.PaymentStatus;

import javax.validation.constraints.DecimalMin;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;


@Data
public class PaymentResultDTO implements Serializable {

    private static final long serialVersionUID = 2264816174050483391L;

    private Long id;

    private Long invoiceId;

    private Long policyHolderId;

    private String policyHolderName;

    private Long employerId;

    private String employerName;

    private String paymentChannel;

    private String paymentReference;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate paymentDate;

    private double amount;

    private double suspenseAmount;

    private double arrearsAmount;

    String paymentStatus;
}
