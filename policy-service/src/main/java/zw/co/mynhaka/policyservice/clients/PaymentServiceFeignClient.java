package zw.co.mynhaka.policyservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import zw.co.mynhaka.policyservice.domain.dto.payment.PartialWithdrawalPaymentCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.payment.PartialWithdrawalPaymentResultDTO;

import java.util.List;

@FeignClient("payment-service")
public interface PaymentServiceFeignClient {

    @PostMapping(value = "/api/v1/partial-withdrawal/payment", consumes = MediaType.APPLICATION_JSON_VALUE)
    PartialWithdrawalPaymentResultDTO partialWithdrawalPayment(PartialWithdrawalPaymentCreateDTO partialWithdrawalPaymentCreateDTO);

    @PostMapping(value = "/api/v1/partial-withdrawal/payments", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<PartialWithdrawalPaymentResultDTO> partialWithdrawalPayment(List<PartialWithdrawalPaymentCreateDTO> partialWithdrawalPaymentCreateDTO);
}
