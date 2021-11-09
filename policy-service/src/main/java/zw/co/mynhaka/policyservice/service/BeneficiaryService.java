package zw.co.mynhaka.policyservice.service;

import zw.co.mynhaka.policyservice.domain.dto.beneficiary.BeneficiaryCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.beneficiary.BeneficiaryResultDTO;
import zw.co.mynhaka.policyservice.domain.model.Beneficiary;

import java.util.List;

public interface BeneficiaryService {
    BeneficiaryResultDTO save(String policyNumber, BeneficiaryCreateDTO beneficiaryCreateDTO);

    List<BeneficiaryResultDTO> save(String policyNumber, List<BeneficiaryCreateDTO> beneficiaryCreateDTOList);

    BeneficiaryResultDTO findById(Long id);

    List<BeneficiaryResultDTO> findAllByPolicy(String policyNumber);

    Beneficiary getOne(Long id);

    void deleteById(Long id);
}
