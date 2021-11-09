package zw.co.mynhaka.paymentservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.paymentservice.clients.PolicyServiceFeignClient;
import zw.co.mynhaka.paymentservice.domain.dto.payment.PremiumPaymentCreateDTO;
import zw.co.mynhaka.paymentservice.domain.dto.payment.PremiumPaymentResultDTO;
import zw.co.mynhaka.paymentservice.domain.dto.policy.PolicyResultDTO;
import zw.co.mynhaka.paymentservice.domain.model.PremiumPayment;
import zw.co.mynhaka.paymentservice.repository.PremiumPaymentRepository;
import zw.co.mynhaka.paymentservice.service.PremiumPaymentService;
import zw.co.mynhaka.paymentservice.service.mapper.PremiumPaymentMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PremiumPaymentServiceImpl implements PremiumPaymentService {

    private final PremiumPaymentRepository premiumPaymentRepository;
    private final PremiumPaymentMapper premiumPaymentMapper;
    private final PolicyServiceFeignClient policyServiceFeignClient;

    @Override
    public PremiumPaymentResultDTO save(PremiumPaymentCreateDTO paymentCreateDTO) {
        PolicyResultDTO policyResultDTO = policyServiceFeignClient.getPolicy(paymentCreateDTO.getPolicyNumber());

        PremiumPayment premiumPayment = premiumPaymentMapper.toPremiumPayment(paymentCreateDTO);
        return null;
    }

    @Override
    public List<PremiumPaymentResultDTO> save(List<PremiumPaymentCreateDTO> paymentCreateDTO) {
        return null;
    }

    @Override
    public List<PremiumPaymentResultDTO> getAllByPolicyNumber(String policyNumber) {
        return null;
    }
}
