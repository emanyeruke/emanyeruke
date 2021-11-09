package zw.co.mynhaka.polad.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.polad.domain.dtos.BeneficiaryCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.BeneficiaryResultDTO;
import zw.co.mynhaka.polad.domain.dtos.BeneficiaryUpdateDTO;
import zw.co.mynhaka.polad.domain.enums.PersonType;
import zw.co.mynhaka.polad.domain.model.Beneficiary;
import zw.co.mynhaka.polad.domain.model.Policy;
import zw.co.mynhaka.polad.repository.BeneficiaryRepository;
import zw.co.mynhaka.polad.service.exception.EntityNotFoundException;
import zw.co.mynhaka.polad.service.iface.BeneficiaryService;
import zw.co.mynhaka.polad.service.iface.PolicyService;
import zw.co.mynhaka.polad.service.mapper.BeneficiaryMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BeneficiaryServiceImpl implements BeneficiaryService {

    private final BeneficiaryRepository beneficiaryRepository;
    private final BeneficiaryMapper beneficiaryMapper;
    private final PolicyService policyService;

    @Override
    public List<BeneficiaryResultDTO> save(String policyNumber, List<BeneficiaryCreateDTO> beneficiaryCreateDTOList) {
        Policy policy = policyService.getByPolicyNumber(policyNumber);
        Beneficiary beneficiary = new Beneficiary();

        beneficiary.setSumAssured(policy.getSumAssured());

        //beneficiary.calculateSumAssured(beneficiary.getSumAssured());

        List<Beneficiary> beneficiaries = beneficiaryCreateDTOList.stream()
                .map(beneficiaryCreateDTO -> beneficiaryMapper.toBeneficiary(beneficiaryCreateDTO, policy))
                .collect(Collectors.toList());


        return beneficiaryRepository.saveAll(beneficiaries).stream()
                .map(beneficiaryMapper::toBeneficiaryResultDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BeneficiaryResultDTO update(Long id, BeneficiaryUpdateDTO beneficiaryUpdateDTO) {
        Policy policy = policyService.findByPolicyId(beneficiaryUpdateDTO.getPolicyId());
        Beneficiary beneficiary = getOne(id);
        beneficiaryMapper.updateBeneficiary(beneficiary, policy, beneficiaryUpdateDTO);
        return beneficiaryMapper.toBeneficiaryResultDTO(beneficiaryRepository.save(beneficiary));
    }

    @Override
    public BeneficiaryResultDTO findById(Long id) {
        return beneficiaryMapper.toBeneficiaryResultDTO(getOne(id));
    }

    @Override
    public List<BeneficiaryResultDTO> findAllBeneficiariesByPolicy(String policyNumber) {
        Policy policy = policyService.getByPolicyNumber(policyNumber);
        return beneficiaryRepository.findAllByPolicy(policy).stream()
                .filter(beneficiary -> beneficiary.getPersonType() == PersonType.BENEFICIARY)
                .map(beneficiaryMapper::toBeneficiaryResultDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<Beneficiary> findAll() {
        List<Beneficiary> beneficiary = beneficiaryRepository.findAll();
        if (beneficiary.isEmpty()){
            throw new EntityNotFoundException("NO beneficiary available at this time!");
        }
        return beneficiary;
    }

    @Override
    public List<BeneficiaryResultDTO> findAllDependenciesByPolicy(String policyNumber) {
        Policy policy = policyService.getByPolicyNumber(policyNumber);
        return beneficiaryRepository.findAllByPolicy(policy).stream()
                .filter(beneficiary -> beneficiary.getPersonType() != PersonType.BENEFICIARY)
                .map(beneficiaryMapper::toBeneficiaryResultDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BeneficiaryResultDTO> findAllSpousesByPolicy(String policyNumber) {
        Policy policy = policyService.getByPolicyNumber(policyNumber);
        return beneficiaryRepository.findAllByPolicy(policy).stream()
                .filter(beneficiary -> beneficiary.getPersonType() == PersonType.SPOUSE)
                .map(beneficiaryMapper::toBeneficiaryResultDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Beneficiary getOne(Long id) {
        return beneficiaryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Beneficiary with id: " + id + " not found"));
    }

    @Override
    public  List<Beneficiary>findByPolicyNumber(String policyNumber) {
        List<Beneficiary> beneficiaries = beneficiaryRepository.findByPolicyNumber(policyNumber);
        if (beneficiaries.isEmpty()){
            throw new EntityNotFoundException("No Beneficiaries under policy: " + policyNumber);
        }
        return beneficiaries;
    }

    @Override
    public void deleteById(Long id) {
        beneficiaryRepository.deleteById(id);
    }
}
