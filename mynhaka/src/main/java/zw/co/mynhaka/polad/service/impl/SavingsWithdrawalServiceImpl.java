package zw.co.mynhaka.polad.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.polad.domain.dtos.SavingsPartialWithdrawalCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.SavingsPartialWithdrawalResultDTO;
import zw.co.mynhaka.polad.domain.dtos.SavingsPartialWithdrawalUpdateDTO;
import zw.co.mynhaka.polad.domain.model.PolicySavings;
import zw.co.mynhaka.polad.domain.model.PremiumPayment;
import zw.co.mynhaka.polad.domain.model.SavingsPartialWithdrawal;
import zw.co.mynhaka.polad.repository.SavingsPartialWithdrawalRepository;
import zw.co.mynhaka.polad.service.iface.PolicySavingsService;
import zw.co.mynhaka.polad.service.iface.PremiumPaymentService;
import zw.co.mynhaka.polad.service.iface.SavingsWithdrawalService;
import zw.co.mynhaka.polad.service.iface.SurrenderValueCalculatorService;
import zw.co.mynhaka.polad.service.exception.BusinessValidationException;
import zw.co.mynhaka.polad.service.exception.EntityNotFoundException;
import zw.co.mynhaka.polad.service.mapper.SavingsPartialWithdrawalMapper;
import zw.co.mynhaka.polad.service.util.BusinessRulesValidationService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SavingsWithdrawalServiceImpl implements SavingsWithdrawalService {

    private final SavingsPartialWithdrawalRepository savingsPartialWithdrawalRepository;
    private final SavingsPartialWithdrawalMapper savingsPartialWithdrawalMapper;
    private final PolicySavingsService policySavingsService;
    private final PremiumPaymentService premiumPaymentService;
    private final SurrenderValueCalculatorService surrenderValueCalculatorService;

    @Override
    public SavingsPartialWithdrawalResultDTO save(SavingsPartialWithdrawalCreateDTO savingsPartialWithdrawalCreateDTO) {
        String policyNumber = savingsPartialWithdrawalCreateDTO.getPolicyNumber();

        PolicySavings policySavings = policySavingsService.findByPolicyNumber(policyNumber);
        List<PremiumPayment> premiumPayments = premiumPaymentService.getAllValidatedPaymentsByPolicyNumber(policyNumber);

        List<SavingsPartialWithdrawal> withdrawals = getAllByPolicyNumber(policyNumber);

        boolean canBeWithdrawn = withdrawals.size() > 0 ? BusinessRulesValidationService.canBeWithdrawn(premiumPayments, withdrawals.get(0).getInceptionDate()).apply(policySavings)
                : BusinessRulesValidationService.canBeWithdrawn(premiumPayments, null).apply(policySavings);

        if (!canBeWithdrawn)
            throw new BusinessValidationException("Policy can not be partially withdrawn");

        BigDecimal surrenderValue = surrenderValueCalculatorService.calculateValue(premiumPayments);
        BigDecimal maxWithdrawalAmount = surrenderValue.multiply(BigDecimal.valueOf(0.75));

        if (maxWithdrawalAmount.compareTo(savingsPartialWithdrawalCreateDTO.getAmount()) > 0)
            throw new BusinessValidationException("Cannot withdraw more that 75% of the surrender value");

        SavingsPartialWithdrawal savingsPartialWithdrawal = savingsPartialWithdrawalMapper.toSavingsPartialWithdrawal(savingsPartialWithdrawalCreateDTO, policySavings);

        return savingsPartialWithdrawalMapper.toSavingsPartialWithdrawalResultDTO(savingsPartialWithdrawalRepository.save(savingsPartialWithdrawal));
    }

    @Override
    public List<SavingsPartialWithdrawalResultDTO> update(List<SavingsPartialWithdrawalUpdateDTO> savingsPartialWithdrawalUpdateDTOList) {
        List<SavingsPartialWithdrawal> savingsPartialWithdrawals = savingsPartialWithdrawalUpdateDTOList.stream()
                .map(savingsPartialWithdrawalUpdateDTO -> {
                    SavingsPartialWithdrawal savingsPartialWithdrawal = getOne(savingsPartialWithdrawalUpdateDTO.getId());
                    savingsPartialWithdrawalMapper.updateSavingsPartialWithdrawal(savingsPartialWithdrawal, savingsPartialWithdrawalUpdateDTO);
                    return savingsPartialWithdrawal;
                }).collect(Collectors.toList());

        return savingsPartialWithdrawalRepository.saveAll(savingsPartialWithdrawals).stream()
                .map(savingsPartialWithdrawalMapper::toSavingsPartialWithdrawalResultDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<SavingsPartialWithdrawalResultDTO> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return savingsPartialWithdrawalRepository.findAll(pageRequest)
                .map(savingsPartialWithdrawalMapper::toSavingsPartialWithdrawalResultDTO);
    }

    @Override
    public SavingsPartialWithdrawalResultDTO findById(Long id) {
        return savingsPartialWithdrawalMapper.toSavingsPartialWithdrawalResultDTO(getOne(id));
    }

    @Override
    public List<SavingsPartialWithdrawalResultDTO> findAllByPolicyNumber(String policyNumber) {
        return getAllByPolicyNumber(policyNumber).stream()
                .map(savingsPartialWithdrawalMapper::toSavingsPartialWithdrawalResultDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SavingsPartialWithdrawal> getAllByPolicyNumber(String policyNumber) {
        return savingsPartialWithdrawalRepository.findAllByPolicyNumberOrderByCreatedDate(policyNumber);
    }

    @Override
    public SavingsPartialWithdrawal getOne(Long id) {
        return savingsPartialWithdrawalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SavingsPartialWithdrawal with id: " + id + " not found"));
    }

    @Override
    public void deleteById(Long id) {
        savingsPartialWithdrawalRepository.deleteById(id);
    }
}
