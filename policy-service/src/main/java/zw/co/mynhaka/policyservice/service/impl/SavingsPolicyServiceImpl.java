package zw.co.mynhaka.policyservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.policyservice.clients.PolicyHolderServiceFeignClient;
import zw.co.mynhaka.policyservice.domain.dto.cancelpolicy.CancelPolicyCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.cancelpolicy.CancelPolicyResultDTO;
import zw.co.mynhaka.policyservice.domain.dto.savingspolicy.SavingsPolicyCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.savingspolicy.SavingsPolicyResultDTO;
import zw.co.mynhaka.policyservice.domain.enums.PolicyState;
import zw.co.mynhaka.policyservice.domain.model.Agent;
import zw.co.mynhaka.policyservice.domain.model.PolicyHolder;
import zw.co.mynhaka.policyservice.domain.model.SavingsPlan;
import zw.co.mynhaka.policyservice.domain.model.SavingsPolicy;
import zw.co.mynhaka.policyservice.repository.SavingsPolicyRepository;
import zw.co.mynhaka.policyservice.service.SavingsPlanService;
import zw.co.mynhaka.policyservice.service.SavingsPolicyService;
import zw.co.mynhaka.policyservice.service.exceptions.EntityNotFoundException;
import zw.co.mynhaka.policyservice.service.mapper.SavingsPolicyMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SavingsPolicyServiceImpl implements SavingsPolicyService {

    private final SavingsPolicyRepository savingsPolicyRepository;
    private final SavingsPolicyMapper savingsPolicyMapper;
    private final SavingsPlanService savingsPlanService;
    private final PolicyHolderServiceFeignClient policyHolderServiceFeignClient;

    @Override
    public SavingsPolicy save(SavingsPolicy savingsPolicy) {
        return savingsPolicyRepository.save(savingsPolicy);
    }

    @Override
    public SavingsPolicyResultDTO allocateProduct(SavingsPolicyCreateDTO savingsPolicyCreateDTO) {
        //Checks if policyHolder exists
        PolicyHolder policyHolder = policyHolderServiceFeignClient.getPolicyHolder(savingsPolicyCreateDTO.getPolicyHolderId());
        log.info("#################### Policyholder found {}", policyHolder.toString());
        //Checks if agent exists
        Agent agent = policyHolderServiceFeignClient.getAgent(savingsPolicyCreateDTO.getAgentId());
        log.info("#################### Agent found {}", agent.toString());
        SavingsPlan savingsPlan = savingsPlanService.getOne(savingsPolicyCreateDTO.getSavingsPlanId());

        SavingsPolicy savingsPolicy = savingsPolicyMapper.toSavingsPolicy(savingsPolicyCreateDTO, savingsPlan);

        log.info("###################Response: {}", savingsPolicy.toString());
        return savingsPolicyMapper.toSavingsPolicyResultDTO(savingsPolicyRepository.save(savingsPolicy));
    }

    @Override
    public SavingsPolicy getOne(Long id) {
        return savingsPolicyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SavingsPolicy", "id", id));
    }

    @Override
    public SavingsPolicyResultDTO findById(Long id) {
        return savingsPolicyMapper.toSavingsPolicyResultDTO(getOne(id));
    }

    @Override
    public Page<SavingsPolicyResultDTO> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return savingsPolicyRepository.findAll(pageRequest).map(savingsPolicyMapper::toSavingsPolicyResultDTO);
    }

    @Override
    public List<SavingsPolicy> findPolicySavingsByPolicyHolder(Long policyHolderId) {
        return savingsPolicyRepository.findAllByPolicyHolderId(policyHolderId);
    }

    @Override
    public List<SavingsPolicy> findPolicySavingsByPolicyHolderAndNextInvoicingDate(Long policyHolderId, LocalDate nextInvoicingDate) {
        return null;
    }

    @Override
    public List<SavingsPolicyResultDTO> findByPolicyHolderId(Long id) {
        return findPolicySavingsByPolicyHolder(id).stream()
                .map(savingsPolicyMapper::toSavingsPolicyResultDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SavingsPolicyResultDTO findByPolicyNumber(String policyNumber) {
        return savingsPolicyMapper.toSavingsPolicyResultDTO(getOneByPolicyNumber(policyNumber));
    }

    @Override
    public SavingsPolicy getOneByPolicyNumber(String policyNumber) {
        return savingsPolicyRepository.findByPolicyNumber(policyNumber)
                .orElseThrow(() -> new EntityNotFoundException("SavingsPolicy", "policyNumber", policyNumber));
    }

    @Override
    public CancelPolicyResultDTO cancelPolicy(CancelPolicyCreateDTO cancelPolicyCreateDto) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        SavingsPolicy savingsPolicy = getOne(id);
        savingsPolicy.setPolicyState(PolicyState.DELETED);
        savingsPolicyRepository.save(savingsPolicy);
    }

    @Override
    public void allocateFakerProduct() {

    }
}
