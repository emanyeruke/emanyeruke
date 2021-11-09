package zw.co.mynhaka.polad.service.iface;

import zw.co.mynhaka.polad.domain.dtos.accident.ClaimAccidentResultDTO;
import zw.co.mynhaka.polad.domain.dtos.accident.PolicyAccidentUpdateCreateDto;
import zw.co.mynhaka.polad.domain.dtos.claim.ClaimAccidentCreateDto;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicyAccidentResultDTO;
import zw.co.mynhaka.polad.domain.model.PolicyAccident;
import zw.co.mynhaka.polad.domain.model.PolicyHolder;
import zw.co.mynhaka.polad.domain.model.Transaction;

import java.time.LocalDate;
import java.util.List;

public interface PolicyAccidentService {

    String allocateProduct(PolicyAccident policyAccident);

    PolicyAccident getOne(Long id);

//    BeneficiaryAccidentResultDTO addAccidentSpouse(SpouseCreateDto spouseCreateDto);

//    List<BeneficiaryAccidentResultDTO> getBeneficiariesById(Long policyId);

//    List<BeneficiaryAccidentResultDTO> getBeneficiariesByPolicyNumber(String policyNumber);

    PolicyAccidentResultDTO findById(Long id);

    List<PolicyAccident> findPolicyAccidentByPolicyHolder(PolicyHolder policyHolder);

    List<PolicyAccident> findPolicyAccidentByPolicyHolderAndNextInvoicingDate(PolicyHolder policyHolder, LocalDate invoicingDate);

    List<PolicyAccidentResultDTO> findByPolicyHolderId(PolicyHolder policyHolder);

   // Page<PolicyAccidentResultDTO> findAll(Pageable pageable);

    List<PolicyAccident> getAll();


    void delete(Long id);

    PolicyAccident findByPolicyNumber(String policyNumber);

    Transaction cancelPolicy(String policyNumber);

    Transaction refundPolicy(String policyNumber);

    ClaimAccidentResultDTO addClaim(ClaimAccidentCreateDto claimAccidentCreateDto);

    void allocateFakerProduct();


    List<PolicyAccident> findPolicyAccidentByPolicyHolder_Id(Long id);

    PolicyAccidentResultDTO changePaymentMethodAndFrequency(PolicyAccidentUpdateCreateDto policyAccidentCreateDto);

    PolicyAccidentResultDTO upgradeProduct(Long policyId, Long accidentId);

    PolicyAccidentResultDTO downgradeProduct(Long policyId, Long accidentId);



}
