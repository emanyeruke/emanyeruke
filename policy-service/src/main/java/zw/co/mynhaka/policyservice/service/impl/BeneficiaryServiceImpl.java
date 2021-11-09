package zw.co.mynhaka.policyservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.policyservice.domain.dto.beneficiary.BeneficiaryCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.beneficiary.BeneficiaryResultDTO;
import zw.co.mynhaka.policyservice.domain.model.Beneficiary;
import zw.co.mynhaka.policyservice.domain.model.Policy;
import zw.co.mynhaka.policyservice.repository.BeneficiaryRepository;
import zw.co.mynhaka.policyservice.service.BeneficiaryService;
import zw.co.mynhaka.policyservice.service.PolicyService;
import zw.co.mynhaka.policyservice.service.exceptions.EntityNotFoundException;
import zw.co.mynhaka.policyservice.service.mapper.BeneficiaryMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BeneficiaryServiceImpl implements BeneficiaryService {

    private final BeneficiaryRepository beneficiaryRepository;
    private final BeneficiaryMapper beneficiaryMapper;
    private final PolicyService policyService;


    @Override
    public BeneficiaryResultDTO save(String policyNumber, BeneficiaryCreateDTO beneficiaryCreateDTO) {
        Policy policy = policyService.getByPolicyNumber(policyNumber);

        Beneficiary beneficiary = beneficiaryMapper.toBeneficiary(beneficiaryCreateDTO, policy);
        return beneficiaryMapper.toBeneficiaryResultDTO(beneficiaryRepository.save(beneficiary));
    }

    @Override
    public List<BeneficiaryResultDTO> save(String policyNumber, List<BeneficiaryCreateDTO> beneficiaryCreateDTOList) {
        Policy policy = policyService.getByPolicyNumber(policyNumber);

        List<Beneficiary> beneficiaries = beneficiaryCreateDTOList.stream()
                .map(beneficiaryCreateDTO -> beneficiaryMapper.toBeneficiary(beneficiaryCreateDTO, policy))
                .collect(Collectors.toList());

        return beneficiaryRepository.saveAll(beneficiaries).stream()
                .map(beneficiaryMapper::toBeneficiaryResultDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BeneficiaryResultDTO findById(Long id) {
        return beneficiaryMapper.toBeneficiaryResultDTO(getOne(id));
    }

    @Override
    public List<BeneficiaryResultDTO> findAllByPolicy(String policyNumber) {
        Policy policy = policyService.getByPolicyNumber(policyNumber);
        return beneficiaryRepository.findAllByPolicy(policy).stream()
                .map(beneficiaryMapper::toBeneficiaryResultDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Beneficiary getOne(Long id) {
        return beneficiaryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Beneficiary", "id", id));
    }

    @Override
    public void deleteById(Long id) {
        beneficiaryRepository.deleteById(id);
    }
}
