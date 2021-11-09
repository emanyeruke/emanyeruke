package zw.co.mynhaka.polad.service.iface;

import zw.co.mynhaka.polad.domain.dtos.beneficiary.funeral.BeneficiaryFuneralResultDTO;
import zw.co.mynhaka.polad.domain.dtos.claim.DeathClaimCreateDto;
import zw.co.mynhaka.polad.domain.dtos.funeral.ClaimFuneralResultDTO;
import zw.co.mynhaka.polad.domain.dtos.funeral.FuneralBeneficiaryCreateDto;
import zw.co.mynhaka.polad.domain.dtos.funeral.FuneralDeathBeneficiaryCreateDto;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicyFuneralResultDTO;
import zw.co.mynhaka.polad.domain.model.PolicyFuneral;
import zw.co.mynhaka.polad.domain.model.PolicyHolder;
import zw.co.mynhaka.polad.domain.model.Transaction;

import java.time.LocalDate;
import java.util.List;

public interface PolicyFuneralService {

    String allocateProduct(PolicyFuneral policyFuneral);

    PolicyFuneral getOne(Long id);

    PolicyFuneralResultDTO findById(Long id);

    List<BeneficiaryFuneralResultDTO> addBeneficiary(List<FuneralBeneficiaryCreateDto> funeralBeneficiaryCreateDto);

    List<BeneficiaryFuneralResultDTO> addDeathBeneficiary(List<FuneralDeathBeneficiaryCreateDto> deathBeneficiaryCreateDto);

    List<PolicyFuneral> findAll();

    List<PolicyFuneral> findPolicyFuneralByPolicyHolder(PolicyHolder policyHolder);

    List<PolicyFuneral> findPolicyFuneralByPolicyHolderAndNextInvoicingDate(PolicyHolder policyHolder, LocalDate localDate);

    List<PolicyFuneralResultDTO> findByPolicyHolderId(Long id);

    PolicyFuneral findByPolicyNumber(String policyNumber);

    List<BeneficiaryFuneralResultDTO> getBeneficiariesById(Long policyId);

    List<BeneficiaryFuneralResultDTO> getBeneficiariesByPolicyNumber(String policyNumber);

    Transaction cancelPolicy(String policyNumber);

    Transaction refundPolicy(String policyNumber);

    ClaimFuneralResultDTO addClaim(DeathClaimCreateDto deathClaimCreateDto);

    void allocateFakerProduct();

    List<PolicyFuneral> findPolicyFuneralByPolicyHolder_Id(Long id);

    PolicyFuneralResultDTO upgradeProduct(Long policyId, Long funeralId);

    PolicyFuneralResultDTO downgradeProduct(Long policyId, Long funeralId);

    List<BeneficiaryFuneralResultDTO> getDependentsById(Long id);

    List<BeneficiaryFuneralResultDTO> getDependentsByPolicyNumber(String policynumber);

    void deleteBeneficiaryOrDependent(Long beneficiaryId);
}