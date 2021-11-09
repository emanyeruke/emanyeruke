package zw.co.mynhaka.policyservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.policyservice.clients.PaymentServiceFeignClient;
import zw.co.mynhaka.policyservice.domain.dto.payment.PartialWithdrawalPaymentCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.payment.WithdrawalPaymentCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.savingspartialwithdrawal.SavingsPartialWithdrawalCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.savingspartialwithdrawal.SavingsPartialWithdrawalResultDTO;
import zw.co.mynhaka.policyservice.domain.enums.WithdrawalStatus;
import zw.co.mynhaka.policyservice.domain.model.SavingsPartialWithdrawal;
import zw.co.mynhaka.policyservice.domain.model.SavingsPolicy;
import zw.co.mynhaka.policyservice.repository.SavingsWithdrawalRepository;
import zw.co.mynhaka.policyservice.service.SavingsPolicyService;
import zw.co.mynhaka.policyservice.service.SavingsWithdrawalService;
import zw.co.mynhaka.policyservice.service.exceptions.BusinessRulesValidationException;
import zw.co.mynhaka.policyservice.service.exceptions.EntityNotFoundException;
import zw.co.mynhaka.policyservice.service.mapper.PaymentMapper;
import zw.co.mynhaka.policyservice.service.mapper.SavingsWithdrawalMapper;
import zw.co.mynhaka.policyservice.service.utils.BusinessRulesValidationService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SavingsWithdrawalServiceImpl implements SavingsWithdrawalService {

    private final SavingsWithdrawalRepository savingsWithdrawalRepository;
    private final SavingsWithdrawalMapper savingsWithdrawalMapper;
    private final SavingsPolicyService savingsPolicyService;
    private final PaymentMapper paymentMapper;
    private final PaymentServiceFeignClient paymentServiceFeignClient;

    @Override
    public SavingsPartialWithdrawalResultDTO save(String policyNumber, SavingsPartialWithdrawalCreateDTO savingsPartialWithdrawalCreateDTO) {
        SavingsPolicy savingsPolicy = savingsPolicyService.getOneByPolicyNumber(policyNumber);

        if (BusinessRulesValidationService.canPolicyBeWithdrawnOrSurrendered().apply(savingsPolicy))
            throw new BusinessRulesValidationException("Policy cannot be withdrawn");

        if (BusinessRulesValidationService.isWithdrawalAmountAcceptable(savingsPartialWithdrawalCreateDTO.getAmount()).apply(savingsPolicy))
            throw new BusinessRulesValidationException("Amount exceeds maximum acceptable withdrawal amount");

        SavingsPartialWithdrawal savingsPartialWithdrawal = savingsWithdrawalMapper
                .toSavingsPartialWithdrawal(savingsPartialWithdrawalCreateDTO, savingsPolicy);

        return savingsWithdrawalMapper.toSavingsPartialWithdrawalResultDTO(savingsWithdrawalRepository.save(savingsPartialWithdrawal));
    }

    @Override
    public SavingsPartialWithdrawalResultDTO approveWithdrawal(Long withdrawalId) {
        SavingsPartialWithdrawal savingsPartialWithdrawal = getOne(withdrawalId);
        savingsPartialWithdrawal.setWithdrawalStatus(WithdrawalStatus.APPROVED);
        return savingsWithdrawalMapper.toSavingsPartialWithdrawalResultDTO(savingsWithdrawalRepository.save(savingsPartialWithdrawal));
    }

    @Override
    public SavingsPartialWithdrawalResultDTO approveWithdrawalPayment(WithdrawalPaymentCreateDTO withdrawalPaymentCreateDTO) {
        SavingsPartialWithdrawal savingsPartialWithdrawal = getOne(withdrawalPaymentCreateDTO.getWithdrawalId());
        SavingsPolicy savingsPolicy = savingsPolicyService.getOne(withdrawalPaymentCreateDTO.getSavingsPolicyId());

        PartialWithdrawalPaymentCreateDTO paymentCreateDTO = paymentMapper.toPartialWithdrawalPaymentCreateDTO(withdrawalPaymentCreateDTO, savingsPolicy);
        paymentServiceFeignClient.partialWithdrawalPayment(paymentCreateDTO);

        savingsPartialWithdrawal.setWithdrawalStatus(WithdrawalStatus.PAID);
        return savingsWithdrawalMapper.toSavingsPartialWithdrawalResultDTO(savingsWithdrawalRepository.save(savingsPartialWithdrawal));
    }

    @Override
    public List<SavingsPartialWithdrawalResultDTO> approveWithdrawalPayment(List<WithdrawalPaymentCreateDTO> withdrawalPaymentCreateDTOList) {
        List<SavingsPartialWithdrawal> savingsPartialWithdrawals = new ArrayList<>();

        List<PartialWithdrawalPaymentCreateDTO> partialWithdrawalPaymentCreateDTOS = withdrawalPaymentCreateDTOList.stream().map(withdrawalPaymentCreateDTO -> {
            savingsPartialWithdrawals.add(getOne(withdrawalPaymentCreateDTO.getWithdrawalId()));
            SavingsPolicy savingsPolicy = savingsPolicyService.getOne(withdrawalPaymentCreateDTO.getSavingsPolicyId());

            return paymentMapper.toPartialWithdrawalPaymentCreateDTO(withdrawalPaymentCreateDTO, savingsPolicy);
        }).collect(Collectors.toList());

        paymentServiceFeignClient.partialWithdrawalPayment(partialWithdrawalPaymentCreateDTOS);

        savingsPartialWithdrawals.forEach(savingsPartialWithdrawal -> savingsPartialWithdrawal.setWithdrawalStatus(WithdrawalStatus.PAID));

        return savingsWithdrawalRepository.saveAll(savingsPartialWithdrawals).stream()
                .map(savingsWithdrawalMapper::toSavingsPartialWithdrawalResultDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<SavingsPartialWithdrawalResultDTO> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return savingsWithdrawalRepository.findAll(pageRequest)
                .map(savingsWithdrawalMapper::toSavingsPartialWithdrawalResultDTO);
    }

    @Override
    public List<SavingsPartialWithdrawalResultDTO> findAll() {
        return savingsWithdrawalRepository.findAll().stream()
                .map(savingsWithdrawalMapper::toSavingsPartialWithdrawalResultDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SavingsPartialWithdrawal getOne(Long id) {
        return savingsWithdrawalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SavingsWithdrawal", "id", id));
    }

    @Override
    public void deleteById(Long withdrawalId) {
        savingsWithdrawalRepository.deleteById(withdrawalId);
    }
}
