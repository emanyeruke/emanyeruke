package zw.co.mynhaka.policyservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.policyservice.domain.dto.policy.PolicyResultDTO;
import zw.co.mynhaka.policyservice.domain.model.Policy;
import zw.co.mynhaka.policyservice.repository.PolicyRepository;
import zw.co.mynhaka.policyservice.service.PolicyService;
import zw.co.mynhaka.policyservice.service.exceptions.EntityNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PolicyServiceImpl implements PolicyService {

    private final PolicyRepository policyRepository;

    @Override
    public List<PolicyResultDTO> findAll(int page, int size) {
        return null;
    }

    @Override
    public PolicyResultDTO findById(Long id) {
        return null;
    }

    @Override
    public PolicyResultDTO findByPolicyNumber(String policyNumber) {
        return null;
    }

    @Override
    public Policy getByPolicyNumber(String policyNumber) {
        return policyRepository.findByPolicyNumber(policyNumber)
                .orElseThrow(() -> new EntityNotFoundException("Policy", "policyNumber", policyNumber));
    }

    @Override
    public Policy findByPolicyId(Long policyId) {
        return policyRepository.findById(policyId)
                .orElseThrow(() -> new EntityNotFoundException("Policy", "id", policyId));
    }
}
