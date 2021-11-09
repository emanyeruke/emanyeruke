package zw.co.mynhaka.polad.service.iface;

import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicySavingsResultDTO;
import zw.co.mynhaka.polad.domain.model.PolicySavings;

import java.util.List;

public interface PolicySavingsService {

    String allocateProduct(PolicySavings policySavings);

    List<PolicySavings> findAll();

    List<PolicySavingsResultDTO> findAllByPolicyHolder(Long id);

    PolicySavingsResultDTO findById(Long id);

    PolicySavings getOne(Long id);

//    List<PolicySavings> findPolicySavingsByPolicyHolder(PolicyHolder policyHolder);

    PolicySavings findByPolicyNumber(String policyNumber);

//    CancelPolicyResultDTO cancelPolicy(CancelPolicyCreateDTO cancelPolicyCreateDto);

    void allocateFakerProduct();

    PolicySavingsResultDTO upgradeProduct(Long policyId, Long savingsId);

    PolicySavingsResultDTO downgradeProduct(Long policyId, Long savingsId);

    List<PolicySavings> findPolicySavingsByPolicyHolder_Id(Long id);

}
