package zw.co.mynhaka.polad.service.iface;

import org.springframework.data.domain.Page;
import zw.co.mynhaka.polad.domain.dtos.PolicyResultDTO;
import zw.co.mynhaka.polad.domain.model.Policy;
import zw.co.mynhaka.polad.domain.model.PolicyHolder;

import java.util.List;

public interface PolicyService {
    Page<PolicyResultDTO> findAll(int page, int size);

    Policy getOne(Long id);

    List<Policy> getAll();

    Policy findByPolicyNumber(String policyNumber);

    List<Policy> getAllByPolicyHolder(PolicyHolder policyHolder);

    Policy getByPolicyNumber(String policyNumber);

    Policy findByPolicyId(Long policyId);
}
