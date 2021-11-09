package zw.co.mynhaka.policyservice.service;

import zw.co.mynhaka.policyservice.domain.dto.policy.PolicyResultDTO;
import zw.co.mynhaka.policyservice.domain.model.Policy;

import java.util.List;

public interface PolicyService {
    List<PolicyResultDTO> findAll(int page, int size);

    PolicyResultDTO findById(Long id);

    PolicyResultDTO findByPolicyNumber(String policyNumber);

    Policy getByPolicyNumber(String policyNumber);

    Policy findByPolicyId(Long policyId);
}
