package zw.co.mynhaka.paymentservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.paymentservice.domain.dto.payment.PartialWithdrawalPaymentCreateDTO;
import zw.co.mynhaka.paymentservice.domain.dto.payment.PartialWithdrawalPaymentResultDTO;
import zw.co.mynhaka.paymentservice.domain.model.PartialWithdrawalPayment;
import zw.co.mynhaka.paymentservice.repository.PartialWithdrawalPaymentRepository;
import zw.co.mynhaka.paymentservice.service.PartialWithdrawalPaymentService;
import zw.co.mynhaka.paymentservice.service.mapper.PartialWithdrawalPaymentMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PartialWithdrawalPaymentServiceImpl implements PartialWithdrawalPaymentService {

    private final PartialWithdrawalPaymentRepository partialWithdrawalPaymentRepository;
    private final PartialWithdrawalPaymentMapper partialWithdrawalPaymentMapper;

    @Override
    public PartialWithdrawalPaymentResultDTO save(PartialWithdrawalPaymentCreateDTO partialWithdrawalPaymentCreateDTO) {
        PartialWithdrawalPayment partialWithdrawalPayment = partialWithdrawalPaymentMapper.toPartialWithdrawalPayment(partialWithdrawalPaymentCreateDTO);
        return partialWithdrawalPaymentMapper.toPartialWithdrawalPaymentResultDTO(partialWithdrawalPaymentRepository.save(partialWithdrawalPayment));
    }

    @Override
    public List<PartialWithdrawalPaymentResultDTO> save(List<PartialWithdrawalPaymentCreateDTO> partialWithdrawalPaymentCreateDTOList) {
        List<PartialWithdrawalPayment> withdrawalPayments = partialWithdrawalPaymentCreateDTOList.stream()
                .map(partialWithdrawalPaymentMapper::toPartialWithdrawalPayment)
                .collect(Collectors.toList());

        return partialWithdrawalPaymentRepository.saveAll(withdrawalPayments).stream()
                .map(partialWithdrawalPaymentMapper::toPartialWithdrawalPaymentResultDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PartialWithdrawalPaymentResultDTO> findAllByPolicyNumber(String policyNumber) {
        return partialWithdrawalPaymentRepository.findAllByPolicyNumber(policyNumber).stream()
                .map(partialWithdrawalPaymentMapper::toPartialWithdrawalPaymentResultDTO)
                .collect(Collectors.toList());
    }
}
