package zw.co.mynhaka.polad.service.iface;

import zw.co.mynhaka.polad.domain.dtos.beneficiary.comprehensive.BeneficiaryComprehensiveResultDTO;
import zw.co.mynhaka.polad.domain.dtos.claim.DeathClaimCreateDto;
import zw.co.mynhaka.polad.domain.dtos.comprehensivefuneral.ClaimComprehensiveResultDTO;
import zw.co.mynhaka.polad.domain.dtos.comprehensivefuneral.ComprehensiveBeneficiaryDto;
import zw.co.mynhaka.polad.domain.dtos.comprehensivefuneral.DeathBeneficiaryDto;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicyComprehensiveResultDTO;
import zw.co.mynhaka.polad.domain.model.PolicyComprehensive;
import zw.co.mynhaka.polad.domain.model.PolicyHolder;
import zw.co.mynhaka.polad.domain.model.Transaction;

import java.time.LocalDate;
import java.util.List;

public interface PolicyComprehensiveService {

    String allocateProduct(PolicyComprehensive policyComprehensive);

    PolicyComprehensive getOne(Long id);

    PolicyComprehensiveResultDTO findById(Long id);

    List<BeneficiaryComprehensiveResultDTO> addBeneficiary(List<ComprehensiveBeneficiaryDto> comprehensiveBeneficiaryDto);

    List<BeneficiaryComprehensiveResultDTO> addDeathBeneficiary(List<DeathBeneficiaryDto> deathBeneficiaryDto);

    List<PolicyComprehensive> findAll();

    List<PolicyComprehensive> findPolicyComprehensiveByPolicyHolder(PolicyHolder policyHolder);

    List<PolicyComprehensive> findPolicyComprehensiveByPolicyHolderAndNextInvoicingDate(PolicyHolder policyHolder, LocalDate localDate);

    List<PolicyComprehensiveResultDTO> findByPolicyHolderId(Long id);

    PolicyComprehensive findByPolicyNumber(String policyNumber);

    List<BeneficiaryComprehensiveResultDTO> getBeneficiariesById(Long policyId);

    List<BeneficiaryComprehensiveResultDTO> getBeneficiariesByPolicyNumber(String policyNumber);

    Transaction cancelPolicy(String policyNumber);

    Transaction refundPolicy(String policyNumber);

    ClaimComprehensiveResultDTO addClaim(DeathClaimCreateDto deathClaimCreateDto);

    void allocateFakerProduct();

    List<PolicyComprehensive> findPolicyComprehensiveByPolicyHolder_Id(Long id);


    PolicyComprehensiveResultDTO upgradeProduct(Long policyId, Long comprehensiveId);

    PolicyComprehensiveResultDTO downgradeProduct(Long policyId, Long comprehensiveId);

    List<BeneficiaryComprehensiveResultDTO> getDependentsById(Long id);

    List<BeneficiaryComprehensiveResultDTO> getDependentsByPolicyNumber(String policynumber);

    void deleteBeneficiaryOrDependent(Long beneficiaryId);
}
