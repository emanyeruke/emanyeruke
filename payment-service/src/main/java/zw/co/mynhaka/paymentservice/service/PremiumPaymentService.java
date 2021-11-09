package zw.co.mynhaka.paymentservice.service;

import zw.co.mynhaka.paymentservice.domain.dto.payment.PremiumPaymentCreateDTO;
import zw.co.mynhaka.paymentservice.domain.dto.payment.PremiumPaymentResultDTO;

import java.util.List;

public interface PremiumPaymentService {
    PremiumPaymentResultDTO save(final PremiumPaymentCreateDTO paymentCreateDTO);

    List<PremiumPaymentResultDTO> save(final List<PremiumPaymentCreateDTO> paymentCreateDTO);

    List<PremiumPaymentResultDTO> getAllByPolicyNumber(String policyNumber);
}
