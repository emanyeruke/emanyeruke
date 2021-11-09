package zw.co.mynhaka.policyholderservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.policyholderservice.domain.dto.policyholder.PolicyHolderCreateDTO;
import zw.co.mynhaka.policyholderservice.domain.dto.policyholder.PolicyHolderResultDTO;
import zw.co.mynhaka.policyholderservice.domain.dto.policyholder.PolicyHolderUpdateDTO;
import zw.co.mynhaka.policyholderservice.domain.models.Employer;
import zw.co.mynhaka.policyholderservice.domain.models.PolicyHolder;
import zw.co.mynhaka.policyholderservice.repository.PolicyHolderRepository;
import zw.co.mynhaka.policyholderservice.service.EmployerService;
import zw.co.mynhaka.policyholderservice.service.PolicyHolderService;
import zw.co.mynhaka.policyholderservice.service.exceptions.BusinessValidationException;
import zw.co.mynhaka.policyholderservice.service.mapper.PolicyHolderMapper;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static zw.co.mynhaka.policyholderservice.service.util.PolicyHolderValidationService.PolicyHolderValidationResult;
import static zw.co.mynhaka.policyholderservice.service.util.PolicyHolderValidationService.isPrincipleAbove18;

@Service
@RequiredArgsConstructor
public class PolicyHolderServiceImpl implements PolicyHolderService {

    private final PolicyHolderRepository policyHolderRepository;
    private final PolicyHolderMapper policyHolderMapper;
    private final EmployerService employerService;

    @Override
    public Page<PolicyHolderResultDTO> findAll(int page, int size) {
        final PageRequest pageRequest = PageRequest.of(page, size);

        return policyHolderRepository.findAllByOrderByLastnameAsc(pageRequest)
                .map(policyHolderMapper::toPolicyHolderResultDTO);
    }

    @Override
    public Page<PolicyHolderResultDTO> findAllByEmployer(Long employerId, int page, int size) {
        Employer employer = employerService.getOne(employerId);
        PageRequest pageRequest = PageRequest.of(page, size);
        return policyHolderRepository.findAllByEmployerOrderByLastnameAsc(employer, pageRequest)
                .map(policyHolderMapper::toPolicyHolderResultDTO);
    }

    @Override
    public PolicyHolderResultDTO findById(Long id) {
        return policyHolderMapper.toPolicyHolderResultDTO(getOne(id));
    }

    @Override
    public PolicyHolderResultDTO save(PolicyHolderCreateDTO policyHolderCreateDTO) {
        final boolean hasEmployer = policyHolderCreateDTO.getEmployerId() != null && policyHolderCreateDTO.getEmployerId() > 0;
        final PolicyHolder policyHolder = policyHolderMapper.toPolicyHolder(policyHolderCreateDTO);

        PolicyHolderValidationResult validationResult = isPrincipleAbove18().apply(policyHolder);

        if ( validationResult == PolicyHolderValidationResult.IS_BELOW_AGE) {
            throw new BusinessValidationException("Principal is below 18 years old");
        }

        PolicyHolder savedPolicyHolder = policyHolderRepository.save(policyHolder);

        if (hasEmployer) {
            Employer employer = employerService.getOne(policyHolderCreateDTO.getEmployerId());
            policyHolder.setEmployer(employer);
        }

        return policyHolderMapper.toPolicyHolderResultDTO(policyHolderRepository.save(savedPolicyHolder));
    }

    @Override
    public PolicyHolderResultDTO save(PolicyHolderUpdateDTO policyHolderUpdateDTO) {
        final boolean hasEmployer = policyHolderUpdateDTO.getEmployerId() != null && policyHolderUpdateDTO.getEmployerId() > 0;
        final PolicyHolder savedPolicyHolder = getOne(policyHolderUpdateDTO.getPolicyHolderId());

        policyHolderMapper.updatePolicyHolder(savedPolicyHolder, policyHolderUpdateDTO);

        if (hasEmployer) {
                Employer employer = employerService.getOne(policyHolderUpdateDTO.getEmployerId());
                savedPolicyHolder.setEmployer(employer);
        } else {
            savedPolicyHolder.setEmployer(null);
        }

        return policyHolderMapper.toPolicyHolderResultDTO(policyHolderRepository.save(savedPolicyHolder));
    }

    @Override
    public void deleteById(Long id) {
        policyHolderRepository.deleteById(id);
    }

    @Override
    public PolicyHolder getOne(Long id) {
        return policyHolderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Policy holder with id: " + id + " not found"));
    }

    @Override
    public PolicyHolder getOneByNationalID(String nationalId) {
        return policyHolderRepository.findByIdNumber(nationalId)
                .orElseThrow(() -> new EntityNotFoundException("Policy holder with national id: " + nationalId + " not found"));
    }

    @Override
    public List<PolicyHolder> findAll() {
        return policyHolderRepository.findAll();
    }

    @Override
    public List<PolicyHolder> findAllByEmployer(Long employerId) {
        Employer employer = employerService.getOne(employerId);
        return policyHolderRepository.findAllByEmployer(employer);
    }

    @Override
    public void saveFaker() {

    }
}
