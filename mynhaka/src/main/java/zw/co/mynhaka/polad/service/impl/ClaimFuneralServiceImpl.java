package zw.co.mynhaka.polad.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.polad.domain.dtos.claim.DeathClaimCreateDto;
import zw.co.mynhaka.polad.domain.dtos.funeral.ClaimFuneralResultDTO;
import zw.co.mynhaka.polad.domain.enums.ClaimStatus;
import zw.co.mynhaka.polad.domain.model.*;
import zw.co.mynhaka.polad.repository.ClaimFuneralRepository;
import zw.co.mynhaka.polad.service.iface.BeneficiaryService;
import zw.co.mynhaka.polad.service.iface.ClaimFuneralService;
import zw.co.mynhaka.polad.service.iface.PolicyFuneralService;
import zw.co.mynhaka.polad.service.iface.PolicyHolderService;
import zw.co.mynhaka.polad.service.exception.BusinessValidationException;
import zw.co.mynhaka.polad.service.exception.EntityNotFoundException;
import zw.co.mynhaka.polad.service.mapper.ClaimFuneralMapper;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClaimFuneralServiceImpl implements ClaimFuneralService {

    private final ClaimFuneralRepository repository;
    private final PolicyHolderService policyHolderService;
    private final ClaimFuneralMapper claimFuneralMapper;
    private final BeneficiaryService beneficiaryService;
    private final PolicyFuneralService policyFuneralService;

    @Override
    public List<ClaimFuneralResultDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(claimFuneralMapper::toClaimFuneralResultDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClaimFuneralResultDTO> findAllByPolicyNumber(String policyNumber) {
        return repository.findByPolicyNumber(policyNumber)
                .stream()
                .map(claimFuneralMapper::toClaimFuneralResultDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClaimFuneralResultDTO findById(Long id) {
        return claimFuneralMapper.toClaimFuneralResultDTO(getOne(id));
    }

    @Override
    public ClaimFuneral add(ClaimFuneral claimFuneral) {

        log.info("########Request to save claim {} ###################");
        PolicyFuneral policyFuneral = policyFuneralService.findByPolicyNumber(claimFuneral.getPolicyNumber());

        Beneficiary beneficiary = (Beneficiary) beneficiaryService.findByPolicyNumber(claimFuneral.getPolicyNumber());//get claim for a specific beneficiary

        if (policyFuneral.getPolicyState().toString().equalsIgnoreCase("CEASED")) {
            throw new BusinessValidationException("Claim cannot be lodged for this policy as has ceased");
        }
            log.info("########Request to save claim Again{} ###################");
            if (claimFuneral.getBeneficiary()!=null){
                //set sum Assured to be retrieved if beneficiary is not null
                claimFuneral.setSumAssured(beneficiary.getSumAssured());
            }else claimFuneral.setSumAssured(policyFuneral.getSumAssured()); //else get sum Assured for the policy holder

            repository.save(claimFuneral);

            return claimFuneral;

        }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public ClaimFuneral getOne(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Claim not found on id " + id));
    }

    @Override
    public ClaimFuneral validateClaim(Long id) {
        ClaimFuneral claimFuneral = getOne(id);
        if (claimFuneral.getClaimStatus() == ClaimStatus.INITIATED) {
            claimFuneral.setClaimStatus(ClaimStatus.VALIDATED);
        } else {
            throw new BusinessValidationException("Claim cannot be validated as it is in " + claimFuneral.getClaimStatus() + " status");
        }

        return repository.save(claimFuneral);
    }

    @Override
    public ClaimFuneral authorizeClaim(Long id) {
        ClaimFuneral claimFuneral = repository.getOne(id);
        if (claimFuneral.getClaimStatus() == ClaimStatus.VALIDATED) {
            claimFuneral.setClaimStatus(ClaimStatus.AUTHORISED_FOR_PAYMENT);
        } else {
            throw new BusinessValidationException("Claim cannot be Authorized as it is in " + claimFuneral.getClaimStatus() + " status");
        }
        return repository.save(claimFuneral);
    }

    @Override
    public ClaimFuneral approveClaim(Long id) {
        ClaimFuneral claimFuneral = repository.getOne(id);
        if (claimFuneral.getClaimStatus() == ClaimStatus.AUTHORISED_FOR_PAYMENT) {
            claimFuneral.setClaimStatus(ClaimStatus.AWAITING_PAYMENT);
        } else {
            throw new BusinessValidationException("Claim cannot be approved as it is in " + claimFuneral.getClaimStatus() + " status");
        }
        return repository.save(claimFuneral);
    }

    @Override
    public ClaimFuneral cancelClaim(Long id) {
        ClaimFuneral claimFuneral = repository.getOne(id);
        if (claimFuneral.getClaimStatus() != ClaimStatus.PAID) {
            claimFuneral.setClaimStatus(ClaimStatus.REJECTED);
        } else {
            throw new BusinessValidationException("Claim cannot be rejected as it is in " + claimFuneral.getClaimStatus() + " status");
        }
        return repository.save(claimFuneral);
    }

    @Override
    public ClaimFuneral payClaim(Long id) {
        ClaimFuneral claimFuneral = repository.getOne(id);
        if (claimFuneral.getClaimStatus() == ClaimStatus.AWAITING_PAYMENT) {
            claimFuneral.setClaimStatus(ClaimStatus.PAID);
            claimFuneral.setDateClaimPaid(Instant.now());
        } else {
            throw new BusinessValidationException("Claim cannot be validated as it is in " + claimFuneral.getClaimStatus() + " status");
        }
        return repository.save(claimFuneral);
    }

    @Override
    public ClaimFuneralResultDTO getOneDto(Long id) {
        return claimFuneralMapper.toClaimFuneralResultDTO(getOne(id));
    }

    @Override
    public Page<ClaimFuneralResultDTO> findAll(PageRequest of) {
        return repository.findAll(of)
                .map(claimFuneralMapper::toClaimFuneralResultDTO);
    }

    @Override
    public List<ClaimFuneralResultDTO> findAllByPolicyHolder(Long id) {
        PolicyHolder policyHolder = policyHolderService.getOne(id);
        return repository.findAllByPolicyHolder(policyHolder)
                .stream()
                .map(claimFuneralMapper::toClaimFuneralResultDTO)
                .collect(Collectors.toList());

    }

    @Override
    public Page<ClaimFuneralResultDTO> findAllByStatus(PageRequest of, String status) {
        return repository.findAllByClaimStatus(status, of)
                .map(claimFuneralMapper::toClaimFuneralResultDTO);
    }
}
