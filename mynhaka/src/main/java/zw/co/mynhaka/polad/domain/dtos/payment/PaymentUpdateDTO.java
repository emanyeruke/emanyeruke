package zw.co.mynhaka.polad.domain.dtos.payment;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import zw.co.mynhaka.polad.domain.enums.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;


@Data
public class PaymentUpdateDTO  {

  //  private Long policyHolderId;

    private PolicyType policyType;

    private PaymentChannel paymentChannel;

    private PaymentMethod paymentMethod;

    private BaseCurrency baseCurrency;

    private String paymentReference;

    private double billedAmount;

    private double receivedAmount;

    //private double suspenseAmount;

    //private double arrearsAmount;

    private  PaymentStatus paymentStatus;

    private LocalDate paymentDate;


}
