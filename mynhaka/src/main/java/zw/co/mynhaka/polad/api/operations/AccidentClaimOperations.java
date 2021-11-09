package zw.co.mynhaka.polad.api.operations;


import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import zw.co.mynhaka.polad.api.response.ApiResponse;
import zw.co.mynhaka.polad.domain.dtos.accident.ClaimAccidentResultDTO;
import zw.co.mynhaka.polad.domain.dtos.claim.AccidentDeathClaimCreateDto;
import zw.co.mynhaka.polad.domain.dtos.claim.ClaimAccidentCreateDto;
import zw.co.mynhaka.polad.domain.dtos.claim.ClaimAccidentUpdateDTO;
import zw.co.mynhaka.polad.domain.enums.AccidentClaimType;
import zw.co.mynhaka.polad.domain.model.ClaimAccident;
import zw.co.mynhaka.polad.domain.model.PolicyAccident;
import zw.co.mynhaka.polad.service.exception.BusinessValidationException;
import zw.co.mynhaka.polad.service.iface.ClaimAccidentService;
import zw.co.mynhaka.polad.service.iface.PolicyAccidentService;

import javax.validation.Valid;
import java.net.URI;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/accident-policy/claim", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccidentClaimOperations {

    private final ClaimAccidentService service;
    private final ModelMapper modelMapper;
    private final PolicyAccidentService policyAccidentService;


    @Autowired
    public AccidentClaimOperations(ClaimAccidentService service, ModelMapper modelMapper, PolicyAccidentService policyAccidentService) {
        this.service = service;
        this.modelMapper = modelMapper;
        this.policyAccidentService = policyAccidentService;
    }


    @PostMapping("injury")
    public ApiResponse injuryAccidentClaim(@Valid @RequestBody ClaimAccidentCreateDto claimAccidentCreateDto) {
        ClaimAccident claimAccident = modelMapper.map(claimAccidentCreateDto,ClaimAccident.class);
        log.info("########Request to save injury claim {} ###################", claimAccidentCreateDto.toString());
        PolicyAccident policyAccident = policyAccidentService.findByPolicyNumber(claimAccidentCreateDto.getPolicyNumber());

        /*if (policyAccident.getPolicyState().toString().equalsIgnoreCase("CEASED"))
            throw new BusinessValidationException("Claim cannot be lodged for this policy as has ceased");
        //set accident claim type

         */
        claimAccident.setAccidentClaimType(AccidentClaimType.INJURY);
        return new ApiResponse(200,"SUCCESS",service.add(claimAccident));
    }


    @PostMapping("death")
    public ApiResponse accidentDeathClaim(@Valid @RequestBody AccidentDeathClaimCreateDto claimAccidentCreateDto) {
        ClaimAccident claimAccident = modelMapper.map(claimAccidentCreateDto,ClaimAccident.class);
        log.info("########Request to save death claim {} ###################", claimAccidentCreateDto.toString());
        //set accident claim type
        claimAccident.setAccidentClaimType(AccidentClaimType.DEATH);
        return new ApiResponse(200,"SUCCESS",service.add(claimAccident));
    }

    @PutMapping("/edit-accident-claim/{claim-id}")
    public ApiResponse updateClaimantStatement(@RequestBody ClaimAccidentUpdateDTO claimAccidentUpdateDTO,
                                                @PathVariable("claim-id") Long claimId){

        ClaimAccident claimAccident = modelMapper.map(claimAccidentUpdateDTO,ClaimAccident.class);

        //update the old record
        ClaimAccident oldRecord = service.getOne(claimId);

        claimAccident.setPolicyHolder(oldRecord.getPolicyHolder());

        return new ApiResponse(200,"SUCCESS", service.update(claimAccident));
    }

  /*  @PutMapping("claim-claimant-accident-injury")
    public ResponseEntity<ClaimAccidentResultDTO> updateClaimAccidentInjury() {
        return null;
    }

    @PutMapping("claim-claimant-employer-statement")
    public ResponseEntity<ClaimAccidentResultDTO> updateClaimEmployerStatement() {
        return null;
    }

    @PutMapping("claim-claimant-physician-statement")
    public ResponseEntity<ClaimAccidentResultDTO> updateClaimPhysicianStatement() {
        return null;
    }

// All not necessary , these are all catered for in the update endpoint above...
   */

    @PutMapping("claim/cancel/{id}")
    public ResponseEntity<ClaimAccidentResultDTO> cancelInitiatedClaim(@PathVariable("id") final Long id) {
        //Create event to notify Delight Assistant.
        log.debug("######### Request to cancel  Claim : {}", id);
        return ResponseEntity.ok(service.cancelClaim(id));

    }

    @PutMapping("claim/validate/{id}")
    public ResponseEntity<ClaimAccidentResultDTO> validateClaim(@PathVariable final Long id) {

        //Create event to notify the Premium Administrator
        log.debug("REST request to generate an invoice : {}", id);
        return ResponseEntity.ok(service.validateClaim(id));
    }

    @PutMapping("claim/authorise/{id}")
    public ResponseEntity<ClaimAccidentResultDTO> authoriseClaim(@PathVariable final Long id) {

        //Create event to notify the Policy Admin Supervisor
        log.debug("REST request to generate an invoice : {}", id);
        return ResponseEntity.ok(service.authorizeClaim(id));
    }

    @PutMapping("claim/initiatepay/{id}")
    public ResponseEntity<ClaimAccidentResultDTO> initiatePayClaim(@PathVariable final Long id) {

        //Create event to notify the CEO to sign
        log.debug("REST request to generate an invoice : {}", id);
        return ResponseEntity.ok(service.approveClaim(id));
    }

    @PutMapping("claim/pay/{id}")
    public ResponseEntity<ClaimAccidentResultDTO> payClaim(@PathVariable final Long id) {

        //Create event to notify the Delight Assistant
        log.debug("REST request to generate an invoice : {}", id);
        return ResponseEntity.ok(service.payClaim(id));
    }


    @GetMapping("claim/{id}")
    public ResponseEntity<ClaimAccidentResultDTO> getClaim(@PathVariable final Long id) {
        log.debug("REST request to get an invoice : {}", id);
        return ResponseEntity.ok(service.getOneDto(id));

    }

    @GetMapping("claims")
    public ApiResponse findAll() {
        return new ApiResponse(200,"SUCCESS",service.findAll());
    }



}
