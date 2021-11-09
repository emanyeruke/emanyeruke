package zw.co.mynhaka.policyservice.service;

import org.springframework.data.domain.Page;
import zw.co.mynhaka.policyservice.domain.dto.cancelpolicy.CancelPolicyCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.cancelpolicy.CancelPolicyResultDTO;
import zw.co.mynhaka.policyservice.domain.dto.savingspolicy.SavingsPolicyCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.savingspolicy.SavingsPolicyResultDTO;
import zw.co.mynhaka.policyservice.domain.model.SavingsPolicy;

import java.time.LocalDate;
import java.util.List;

public interface SavingsPolicyService {
    SavingsPolicyResultDTO allocateProduct(SavingsPolicyCreateDTO savingsPolicyCreateDTO);

    SavingsPolicy getOne(Long id);

    SavingsPolicyResultDTO findById(Long id);

    Page<SavingsPolicyResultDTO> findAll(int page, int size);

    List<SavingsPolicy> findPolicySavingsByPolicyHolder(Long policyHolderId);

    List<SavingsPolicy> findPolicySavingsByPolicyHolderAndNextInvoicingDate(Long policyHolderId, LocalDate nextInvoicingDate);

    List<SavingsPolicyResultDTO> findByPolicyHolderId(Long id);

    SavingsPolicyResultDTO findByPolicyNumber(String policyNumber);

    SavingsPolicy getOneByPolicyNumber(String policyNumber);

    SavingsPolicy save(SavingsPolicy savingsPolicy);

    CancelPolicyResultDTO cancelPolicy(CancelPolicyCreateDTO cancelPolicyCreateDto);

    void deleteById(Long id);

    void allocateFakerProduct();
}
