package zw.co.mynhaka.polad.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.polad.domain.dtos.accident.ClaimAccidentResultDTO;
import zw.co.mynhaka.polad.domain.dtos.claim.ClaimAccidentCreateDto;
import zw.co.mynhaka.polad.domain.enums.ClaimStatus;
import zw.co.mynhaka.polad.domain.enums.PolicyState;
import zw.co.mynhaka.polad.domain.enums.PolicyStatus;
import zw.co.mynhaka.polad.domain.model.Beneficiary;
import zw.co.mynhaka.polad.domain.model.ClaimAccident;
import zw.co.mynhaka.polad.domain.model.PolicyAccident;
import zw.co.mynhaka.polad.events.claim.OnClaimActionEvent;
import zw.co.mynhaka.polad.repository.ClaimAccidentRepository;
import zw.co.mynhaka.polad.service.iface.BeneficiaryService;
import zw.co.mynhaka.polad.service.iface.ClaimAccidentService;
import zw.co.mynhaka.polad.service.iface.PolicyAccidentService;
import zw.co.mynhaka.polad.service.exception.BusinessValidationException;
import zw.co.mynhaka.polad.service.exception.EntityNotFoundException;
import zw.co.mynhaka.polad.service.mapper.claim.ClaimAccidentCreateDtoToClaimAccident;
import zw.co.mynhaka.polad.service.mapper.claim.ClaimAccidentToClaimAccidentResultDTO;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class ClaimAccidentServiceImpl implements ClaimAccidentService {

    private final ApplicationEventPublisher applicationEventPublisher;
    private final ClaimAccidentRepository claimAccidentRepository;
    private final ClaimAccidentCreateDtoToClaimAccident toClaimAccident;
    private final ClaimAccidentToClaimAccidentResultDTO toClaimAccidentResultDTO;
    private final PolicyAccidentService policyAccidentService;
    private  final BeneficiaryService beneficiaryService;

    @Autowired
    public ClaimAccidentServiceImpl(
            final ApplicationEventPublisher applicationEventPublisher,
            final ClaimAccidentRepository claimAccidentRepository,
            final ClaimAccidentCreateDtoToClaimAccident toClaimAccident,
            final ClaimAccidentToClaimAccidentResultDTO toClaimAccidentResultDTO,
            PolicyAccidentService policyAccidentService,
            final BeneficiaryService beneficiaryService) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.claimAccidentRepository = claimAccidentRepository;
        this.toClaimAccident = toClaimAccident;
        this.toClaimAccidentResultDTO = toClaimAccidentResultDTO;
        this.policyAccidentService = policyAccidentService;
        this.beneficiaryService = beneficiaryService;
    }


    @Override
    public List<ClaimAccident> findAll() {
        List<ClaimAccident> claimAccident = claimAccidentRepository.findAll();
        if (claimAccident.isEmpty()){
            throw new EntityNotFoundException("NO accident claims available at this time!");
        }
        return claimAccident;
    }
    @Override
    public List<ClaimAccidentResultDTO> findAllByPolicyNumber(String policyNumber) {
        return claimAccidentRepository.findByPolicyNumber(policyNumber)
                .stream()
                .map(toClaimAccidentResultDTO::convert)
                .collect(Collectors.toList());
    }

    @Override
    public ClaimAccidentResultDTO findById(Long id) {
        return toClaimAccidentResultDTO.convert(claimAccidentRepository.getOne(id));
    }

    @Override
    public ClaimAccident add (ClaimAccident claimAccident) {
        log.info("########Request to save claim {} ###################");
       // Beneficiary beneficiary = beneficiaryService.getOne(claimAccident.getBeneficiary().getId());//get claim for a specific beneficiary

        PolicyAccident policyAccident = policyAccidentService.findByPolicyNumber(claimAccident.getPolicyNumber());

       if (policyAccident.getPolicyState().toString().equalsIgnoreCase("CEASED")) {
            throw new BusinessValidationException("Claim cannot be lodged for this policy as has ceased");
        } /*else {


           log.info("########Request to save claim Again{} ###################");
           if (claimAccident.getBeneficiary() != null) {
               //set sum Assured to be retrieved if beneficiary is not null
               claimAccident.setSumAssured(beneficiary.getSumAssured());
           }*/
            else
               claimAccident.setSumAssured(policyAccident.getSumAssured()); //else get sum Assured for the policy holder


           claimAccidentRepository.save(claimAccident);

           // return "Accident Claim Has been Successfully Processed";
           return claimAccident;

       }



    @Override
    public String update(ClaimAccident claimAccident) {
        Optional<ClaimAccident> claimFromDatabase = claimAccidentRepository.findById(claimAccident.getId());
        if(!claimFromDatabase.isPresent()) throw new EntityNotFoundException("Accident Claim does not exist!");
        //carry date claimed
        claimAccident.setDateCreated(claimFromDatabase.get().getDateCreated());
        claimAccidentRepository.save(claimAccident);
        return "Claim has been successfully updated.";
    }

    @Override
    public void deleteById(Long id) {
        claimAccidentRepository.deleteById(id);
    }

    @Override
    public ClaimAccident getOne(Long id) {
        return claimAccidentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Claim not found"));
    }

    @Override
    public ClaimAccidentResultDTO validateClaim(Long id) {
        ClaimAccident claimAccident = claimAccidentRepository.getOne(id);
        if (claimAccident.getClaimStatus() == ClaimStatus.INITIATED) {
            claimAccident.setClaimStatus(ClaimStatus.VALIDATED);
        } else {
            throw new BusinessValidationException("Claim cannot be validated as it is in " + claimAccident.getClaimStatus() + " status");
        }
        return toClaimAccidentResultDTO.convert(claimAccidentRepository.save(claimAccident));
    }

    @Override
    public ClaimAccidentResultDTO authorizeClaim(Long id) {
        ClaimAccident claimAccident = claimAccidentRepository.getOne(id);
        if (claimAccident.getClaimStatus() == ClaimStatus.VALIDATED) {
            claimAccident.setClaimStatus(ClaimStatus.AUTHORISED_FOR_PAYMENT);
        } else {
            throw new BusinessValidationException("Claim cannot be validated as it is in " + claimAccident.getClaimStatus() + " status");
        }
        return toClaimAccidentResultDTO.convert(claimAccidentRepository.save(claimAccident));
    }

    @Override
    public ClaimAccidentResultDTO approveClaim(Long id) {
        ClaimAccident claimAccident = claimAccidentRepository.getOne(id);
        if (claimAccident.getClaimStatus() == ClaimStatus.AUTHORISED_FOR_PAYMENT) {
            claimAccident.setClaimStatus(ClaimStatus.AWAITING_PAYMENT);
        } else {
            throw new BusinessValidationException("Claim cannot be authorised for payment as it is in " + claimAccident.getClaimStatus() + " status");
        }
        return toClaimAccidentResultDTO.convert(claimAccidentRepository.save(claimAccident));
    }

    @Override
    public ClaimAccidentResultDTO cancelClaim(Long id) {
        ClaimAccident claimAccident = claimAccidentRepository.getOne(id);
        if (claimAccident.getClaimStatus() != ClaimStatus.PAID) {
            claimAccident.setClaimStatus(ClaimStatus.REJECTED);
        } else {
            throw new BusinessValidationException("Claim cannot be cancelled as it is in " + claimAccident.getClaimStatus() + " status");
        }

        return toClaimAccidentResultDTO.convert(claimAccidentRepository.save(claimAccident));
    }

    @Override
    public ClaimAccidentResultDTO payClaim(Long id) {
        ClaimAccident claimAccident = claimAccidentRepository.getOne(id);
        if (claimAccident.getClaimStatus() == ClaimStatus.AWAITING_PAYMENT) {
            claimAccident.setClaimStatus(ClaimStatus.PAID);
            if (claimAccident.getClaimantType().toString().equalsIgnoreCase("PRINCIPAL")) {
                claimAccident.getPolicyAccident().setPolicyState(PolicyState.CEASED);
                claimAccident.getPolicyAccident().setPolicyStatus(PolicyStatus.CLOSED);
            }
            claimAccident.setDateClaimPaid(Instant.now());
        } else {
            throw new BusinessValidationException("Claim cannot be paid as it is in " + claimAccident.getClaimStatus() + " status");
        }
        return toClaimAccidentResultDTO.convert(claimAccidentRepository.save(claimAccident));
    }

    @Override
    public ClaimAccidentResultDTO getOneDto(Long id) {
        return toClaimAccidentResultDTO.convert(claimAccidentRepository.getOne(id));
    }

    @Override
    public Page<ClaimAccidentResultDTO> findAll(PageRequest of) {
        return claimAccidentRepository.findAll(of)
                .map(toClaimAccidentResultDTO::convert);
    }

    @Override
    public List<ClaimAccidentResultDTO> findAllByPolicyHolder(Long id) {
       /* PolicyHolder policyHolder = policyHolderService.getOne(id);
        return claimAccidentRepository.findAllByPolicyHolder(policyHolder)
                .stream()
                .map(toClaimAccidentResultDTO::convert)
                .collect(Collectors.toList());*/
        return null;

    }

    @Override
    public Page<ClaimAccidentResultDTO> findAllByStatus(PageRequest of, String status) {
        return claimAccidentRepository.findAllByClaimStatus(status, of)
                .map(toClaimAccidentResultDTO::convert);
    }


    private void triggerAction(Long id, String action, ClaimAccident claimAccident) {
        OnClaimActionEvent onClaimActionEvent = new OnClaimActionEvent(this, action, "", "");
        applicationEventPublisher.publishEvent(onClaimActionEvent);
    }
}
