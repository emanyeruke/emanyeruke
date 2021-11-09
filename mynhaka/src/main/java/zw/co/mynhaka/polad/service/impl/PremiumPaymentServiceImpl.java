package zw.co.mynhaka.polad.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.polad.domain.dtos.PremiumPaymentCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.PremiumPaymentResultDTO;
import zw.co.mynhaka.polad.domain.dtos.PremiumPaymentUpdateDTO;
import zw.co.mynhaka.polad.domain.enums.PaymentStatus;
import zw.co.mynhaka.polad.domain.model.PremiumPayment;
import zw.co.mynhaka.polad.repository.PremiumPaymentRepository;
import zw.co.mynhaka.polad.service.iface.PremiumPaymentService;
import zw.co.mynhaka.polad.service.exception.EntityNotFoundException;
import zw.co.mynhaka.polad.service.mapper.PremiumPaymentMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PremiumPaymentServiceImpl implements PremiumPaymentService {

    private final PremiumPaymentRepository premiumPaymentRepository;
    private final PremiumPaymentMapper premiumPaymentMapper;

    @Override
    public List<PremiumPaymentResultDTO> save(List<PremiumPaymentCreateDTO> paymentCreateDTOList) {
        List<PremiumPayment> premiumPayments = paymentCreateDTOList.stream()
                .map(premiumPaymentMapper::toPremiumPayment)
                .collect(Collectors.toList());

        return premiumPaymentRepository.saveAll(premiumPayments).stream()
                .map(premiumPaymentMapper::toPremiumPaymentResultDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PremiumPaymentResultDTO> update(List<PremiumPaymentUpdateDTO> paymentUpdateDTOList) {
        List<PremiumPayment> premiumPayments = paymentUpdateDTOList.stream()
                .map(paymentUpdateDTO -> {
                    PremiumPayment premiumPayment = getOne(paymentUpdateDTO.getId());
                    premiumPaymentMapper.updatePremiumPayment(premiumPayment, paymentUpdateDTO);
                    return premiumPayment;
                }).collect(Collectors.toList());

        return premiumPaymentRepository.saveAll(premiumPayments).stream()
                .map(premiumPaymentMapper::toPremiumPaymentResultDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PremiumPaymentResultDTO> getAllByPolicyNumber(String policyNumber) {
        return premiumPaymentRepository.findAllByPolicyNumberOrderByPaymentDate(policyNumber).stream()
                .map(premiumPaymentMapper::toPremiumPaymentResultDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PremiumPayment> getAllValidatedPaymentsByPolicyNumber(String policyNumber) {
        return premiumPaymentRepository.findAllByPolicyNumberOrderByPaymentDate(policyNumber).stream()
                .filter(premiumPayment -> premiumPayment.getPaymentStatus() == PaymentStatus.VALIDATED)
                .collect(Collectors.toList());
    }

    @Override
    public PremiumPayment getOne(Long id) {
        return premiumPaymentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PremiumPayment with id: " + id + " not found"));
    }
}
