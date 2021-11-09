package zw.co.mynhaka.policyservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.mynhaka.policyservice.domain.dto.savingssurrender.SavingsSurrenderCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.savingssurrender.SavingsSurrenderResultDTO;
import zw.co.mynhaka.policyservice.domain.enums.PolicyState;
import zw.co.mynhaka.policyservice.domain.enums.SurrenderStatus;
import zw.co.mynhaka.policyservice.domain.model.SavingsPolicy;
import zw.co.mynhaka.policyservice.domain.model.SavingsSurrender;
import zw.co.mynhaka.policyservice.repository.SavingsSurrenderRepository;
import zw.co.mynhaka.policyservice.service.SavingsPolicyService;
import zw.co.mynhaka.policyservice.service.SavingsSurrenderService;
import zw.co.mynhaka.policyservice.service.exceptions.BusinessRulesValidationException;
import zw.co.mynhaka.policyservice.service.exceptions.EntityNotFoundException;
import zw.co.mynhaka.policyservice.service.mapper.SavingsSurrenderMapper;
import zw.co.mynhaka.policyservice.service.utils.BusinessRulesValidationService;

@Service
@RequiredArgsConstructor
public class SavingsSurrenderServiceImpl implements SavingsSurrenderService {

    private final SavingsSurrenderRepository savingsSurrenderRepository;
    private final SavingsSurrenderMapper savingsSurrenderMapper;
    private final SavingsPolicyService savingsPolicyService;

    @Override
    public SavingsSurrenderResultDTO save(String policyNumber, SavingsSurrenderCreateDTO savingsSurrenderCreateDTO) {
        SavingsPolicy savingsPolicy = savingsPolicyService.getOneByPolicyNumber(policyNumber);

        if (!BusinessRulesValidationService.canPolicyBeWithdrawnOrSurrendered().apply(savingsPolicy))
            throw new BusinessRulesValidationException("Policy can not be surrendered");

        SavingsSurrender savingsSurrender = savingsSurrenderMapper.toSavingsSurrender(savingsSurrenderCreateDTO, savingsPolicy);

        return savingsSurrenderMapper.toSavingsSurrenderResultDTO(savingsSurrenderRepository.save(savingsSurrender));
    }

    @Override
    public SavingsSurrenderResultDTO approveSurrender(Long surrenderId) {
        SavingsSurrender savingsSurrender = getOne(surrenderId);
        savingsSurrender.setSurrenderStatus(SurrenderStatus.APPROVED);
        return savingsSurrenderMapper.toSavingsSurrenderResultDTO(savingsSurrenderRepository.save(savingsSurrender));
    }

    @Override
    @Transactional
    public SavingsSurrenderResultDTO approveSurrenderPayment(Long surrenderId) {
        SavingsSurrender savingsSurrender = getOne(surrenderId);
        //TODO Record Payment
        savingsSurrender.setSurrenderStatus(SurrenderStatus.PAID);

        SavingsPolicy savingsPolicy = savingsSurrender.getSavingsPolicy();
        savingsPolicy.setPolicyState(PolicyState.SURRENDERED);
        return savingsSurrenderMapper.toSavingsSurrenderResultDTO(savingsSurrenderRepository.save(savingsSurrender));
    }

    @Override
    public SavingsSurrender getOne(Long id) {
        return savingsSurrenderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SavingsSurrender", "id", id));
    }

    @Override
    public Page<SavingsSurrenderResultDTO> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return savingsSurrenderRepository.findAll(pageRequest)
                .map(savingsSurrenderMapper::toSavingsSurrenderResultDTO);
    }

    @Override
    public Page<SavingsSurrenderResultDTO> findAllByStatus(int page, int size, String surrenderStatus) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return savingsSurrenderRepository.findAllBySurrenderStatus(SurrenderStatus.valueOf(surrenderStatus), pageRequest)
                .map(savingsSurrenderMapper::toSavingsSurrenderResultDTO);
    }

    @Override
    public void deleteById(Long id) {
        savingsSurrenderRepository.deleteById(id);
    }
}
