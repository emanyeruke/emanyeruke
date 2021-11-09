package zw.co.mynhaka.polad.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.polad.domain.dtos.claim.DeathClaimCreateDto;
import zw.co.mynhaka.polad.domain.dtos.comprehensivefuneral.ClaimComprehensiveResultDTO;
import zw.co.mynhaka.polad.domain.enums.ClaimStatus;
import zw.co.mynhaka.polad.domain.model.*;
import zw.co.mynhaka.polad.repository.ClaimComprehensiveRepository;
import zw.co.mynhaka.polad.service.iface.BeneficiaryService;
import zw.co.mynhaka.polad.service.iface.ClaimComprehensiveService;
import zw.co.mynhaka.polad.service.iface.PolicyComprehensiveService;
import zw.co.mynhaka.polad.service.iface.PolicyHolderService;
import zw.co.mynhaka.polad.service.exception.BusinessValidationException;
import zw.co.mynhaka.polad.service.exception.EntityNotFoundException;
import zw.co.mynhaka.polad.service.mapper.ClaimComprehensiveMapper;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClaimComprehensiveServiceImpl implements ClaimComprehensiveService {

    private final ClaimComprehensiveRepository repository;
    private final PolicyHolderService policyHolderService;
    private final ClaimComprehensiveMapper claimComprehensiveMapper;
    private final BeneficiaryService beneficiaryService;
    private final PolicyComprehensiveService policyComprehensiveService;



    @Override
    public List<ClaimComprehensiveResultDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(claimComprehensiveMapper::toClaimComprehensiveResultDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClaimComprehensiveResultDTO> findAllByPolicyNumber(String policyNumber) {
        return repository.findByPolicyNumber(policyNumber)
                .stream()
                .map(claimComprehensiveMapper::toClaimComprehensiveResultDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClaimComprehensiveResultDTO findById(Long id) {
        return claimComprehensiveMapper.toClaimComprehensiveResultDTO(repository.getOne(id));
    }

    @Override
    public ClaimComprehensive add(ClaimComprehensive claimComprehensive) {
        log.info("########Request to save claim {} ###################");
        //Beneficiary beneficiary = beneficiaryService.getOne(claimComprehensive.getBeneficiary().getId());//get claim for a specific beneficiary

        PolicyComprehensive policyComprehensive = policyComprehensiveService.findByPolicyNumber(claimComprehensive.getPolicyNumber());
        if (policyComprehensive.getPolicyState().toString().equalsIgnoreCase("CEASED")) {
            throw new BusinessValidationException("Claim cannot be lodged for this policy as has ceased");
        } /*else {
            log.info("########Request to save claim Again{} ###################");
            if (claimComprehensive.getBeneficiary()!=null){
                //set sum Assured to be retrieved if beneficiary is not null
                claimComprehensive.setSumAssured(beneficiary.getSumAssured());
            }else claimComprehensive.setSumAssured(policyComprehensive.getSumAssured()); //else get sum Assured for the policy holder
            */


            repository.save(claimComprehensive);

            return claimComprehensive;


    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public ClaimComprehensive getOne(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Claim not found on id " + id)
                );
    }

    @Override
    public ClaimComprehensiveResultDTO validateClaim(Long id) {
        ClaimComprehensive claimComprehensive = repository.getOne(id);
        if (claimComprehensive.getClaimStatus() == ClaimStatus.INITIATED) {
            claimComprehensive.setClaimStatus(ClaimStatus.VALIDATED);
        } else {
            throw new BusinessValidationException("Claim cannot be validated as it is in " + claimComprehensive.getClaimStatus() + " status");
        }
        return claimComprehensiveMapper.toClaimComprehensiveResultDTO(repository.save(claimComprehensive));
    }

    @Override
    public ClaimComprehensiveResultDTO authorizeClaim(Long id) {
        ClaimComprehensive claimComprehensive = repository.getOne(id);
        if (claimComprehensive.getClaimStatus() == ClaimStatus.VALIDATED) {
            claimComprehensive.setClaimStatus(ClaimStatus.AUTHORISED_FOR_PAYMENT);
        } else {
            throw new BusinessValidationException("Claim cannot be validated as it is in " + claimComprehensive.getClaimStatus() + " status");
        }
        return claimComprehensiveMapper.toClaimComprehensiveResultDTO(repository.save(claimComprehensive));
    }

    @Override
    public ClaimComprehensiveResultDTO approveClaim(Long id) {
        ClaimComprehensive claimComprehensive = repository.getOne(id);
        if (claimComprehensive.getClaimStatus() == ClaimStatus.AUTHORISED_FOR_PAYMENT) {
            claimComprehensive.setClaimStatus(ClaimStatus.AWAITING_PAYMENT);
        } else {
            throw new BusinessValidationException("Claim cannot be validated as it is in " + claimComprehensive.getClaimStatus() + " status");
        }
        return claimComprehensiveMapper.toClaimComprehensiveResultDTO(repository.save(claimComprehensive));
    }

    @Override
    public ClaimComprehensiveResultDTO cancelClaim(Long id) {
        ClaimComprehensive claimComprehensive = repository.getOne(id);
        if (claimComprehensive.getClaimStatus() != ClaimStatus.PAID) {
            claimComprehensive.setClaimStatus(ClaimStatus.REJECTED);
        } else {
            throw new BusinessValidationException("Claim cannot be validated as it is in " + claimComprehensive.getClaimStatus() + " status");
        }
        return claimComprehensiveMapper.toClaimComprehensiveResultDTO(repository.save(claimComprehensive));
    }

    @Override
    public ClaimComprehensiveResultDTO payClaim(Long id) {
        ClaimComprehensive claimComprehensive = repository.getOne(id);
        if (claimComprehensive.getClaimStatus() == ClaimStatus.AWAITING_PAYMENT) {
            claimComprehensive.setClaimStatus(ClaimStatus.PAID);
            claimComprehensive.setDateClaimPaid(Instant.now());
        } else {
            throw new BusinessValidationException("Claim cannot be validated as it is in " + claimComprehensive.getClaimStatus() + " status");
        }
        return claimComprehensiveMapper.toClaimComprehensiveResultDTO(repository.save(claimComprehensive));
    }

    @Override
    public ClaimComprehensiveResultDTO getOneDto(Long id) {
        return claimComprehensiveMapper.toClaimComprehensiveResultDTO(getOne(id));
    }

    @Override
    public Page<ClaimComprehensiveResultDTO> findAll(PageRequest of) {
        return repository.findAll(of)
                .map(claimComprehensiveMapper::toClaimComprehensiveResultDTO);
    }

    @Override
    public List<ClaimComprehensiveResultDTO> findAllByPolicyHolder(Long id) {
        PolicyHolder policyHolder = policyHolderService.getOne(id);
        return repository.findAllByPolicyHolder(policyHolder)
                .stream()
                .map(claimComprehensiveMapper::toClaimComprehensiveResultDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ClaimComprehensiveResultDTO> findAllByStatus(PageRequest of, String status) {
        return repository.findAllByClaimStatus(status, of)
                .map(claimComprehensiveMapper::toClaimComprehensiveResultDTO);
    }
}
