package zw.co.mynhaka.polad.service.iface;

import zw.co.mynhaka.polad.domain.dtos.BeneficiaryCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.BeneficiaryResultDTO;
import zw.co.mynhaka.polad.domain.dtos.BeneficiaryUpdateDTO;
import zw.co.mynhaka.polad.domain.model.Beneficiary;

import java.util.List;

public interface BeneficiaryService {
    List<BeneficiaryResultDTO> save(String policyNumber, List<BeneficiaryCreateDTO> beneficiaryCreateDTOList);

    BeneficiaryResultDTO update(Long id, BeneficiaryUpdateDTO beneficiaryUpdateDTO);

    BeneficiaryResultDTO findById(Long id);

    List<Beneficiary> findAll();

    List<BeneficiaryResultDTO> findAllBeneficiariesByPolicy(String policyNumber);

    List<BeneficiaryResultDTO> findAllDependenciesByPolicy(String policyNumber);

    List<BeneficiaryResultDTO> findAllSpousesByPolicy(String policyNumber);

    Beneficiary getOne(Long id);

    List<Beneficiary> findByPolicyNumber(String policyNumber);

    void deleteById(Long id);
}
