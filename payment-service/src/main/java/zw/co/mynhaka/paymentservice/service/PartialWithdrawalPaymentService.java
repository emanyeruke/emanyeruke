package zw.co.mynhaka.paymentservice.service;

import zw.co.mynhaka.paymentservice.domain.dto.payment.PartialWithdrawalPaymentCreateDTO;
import zw.co.mynhaka.paymentservice.domain.dto.payment.PartialWithdrawalPaymentResultDTO;

import java.util.List;

public interface PartialWithdrawalPaymentService {
    PartialWithdrawalPaymentResultDTO save(PartialWithdrawalPaymentCreateDTO partialWithdrawalPaymentCreateDTO);

    List<PartialWithdrawalPaymentResultDTO> save(List<PartialWithdrawalPaymentCreateDTO> partialWithdrawalPaymentCreateDTOList);

    List<PartialWithdrawalPaymentResultDTO> findAllByPolicyNumber(String policyNumber);
}
