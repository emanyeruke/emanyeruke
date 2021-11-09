package zw.co.mynhaka.polad.api.operations;


import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import zw.co.mynhaka.polad.api.response.ApiResponse;
import zw.co.mynhaka.polad.domain.dtos.claim.CancelPolicyCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.claim.DeathClaimCreateDto;
import zw.co.mynhaka.polad.domain.dtos.claim.RefundPolicyCreateDto;
import zw.co.mynhaka.polad.domain.dtos.comprehensivefuneral.ClaimComprehensiveResultDTO;
import zw.co.mynhaka.polad.domain.model.ClaimComprehensive;
import zw.co.mynhaka.polad.service.iface.ClaimComprehensiveService;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/comprehensive-policy/", produces = MediaType.APPLICATION_JSON_VALUE)
public class ComprehensiveClaimOperations {

    private final ClaimComprehensiveService service;
    private final ModelMapper modelMapper;

    @Autowired
    public ComprehensiveClaimOperations(ClaimComprehensiveService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @PostMapping("claim")
  public ApiResponse registerClaim(@RequestBody DeathClaimCreateDto deathClaimCreateDto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        ClaimComprehensive claimComprehensive = modelMapper.map(deathClaimCreateDto,ClaimComprehensive.class);
        return new ApiResponse(200,"SUCCESS",service.add(claimComprehensive));
    }



    @PutMapping("claim/refund")
    ResponseEntity<ClaimComprehensiveResultDTO> refundPolicy(@PathVariable("id")  Long id) {
        return null;
    }


    @PutMapping("claim/cancel/{id}")
    public ResponseEntity<ClaimComprehensiveResultDTO> cancelInitiatedClaim(@PathVariable("id")  Long id) {
        log.debug("REST request to get an invoice : {}", id);
        return ResponseEntity.ok(service.cancelClaim(id));

    }


    @PutMapping("claim/validate/{id}")
    public ResponseEntity<ClaimComprehensiveResultDTO> validateClaim(@PathVariable final Long id) {

        //Create event to notify the Premium Administrator
        log.debug("REST request to generate an invoice : {}", id);
        return ResponseEntity.ok(service.validateClaim(id));
    }

    @PutMapping("claim/authorise/{id}")
    public ResponseEntity<ClaimComprehensiveResultDTO> authoriseClaim(@PathVariable final Long id) {

        //Create event to notify the Policy Admin Supervisor
        log.debug("REST request to generate an invoice : {}", id);
        return ResponseEntity.ok(service.authorizeClaim(id));
    }

    @PutMapping("claim/initiatepay/{id}")
    public ResponseEntity<ClaimComprehensiveResultDTO> initiatepayClaim(@PathVariable final Long id) {

        //Create event to notify the CEO to sign
        log.debug("REST request to generate an invoice : {}", id);
        return ResponseEntity.ok(service.approveClaim(id));
    }

    @PutMapping("claim/pay/{id}")
    public ResponseEntity<ClaimComprehensiveResultDTO> payClaim(@PathVariable final Long id) {

        //Create event to notify the Delight Assistant
        log.debug("REST request to generate an invoice : {}", id);
        return ResponseEntity.ok(service.payClaim(id));
    }


    @GetMapping("claim/{id}")
    public ResponseEntity<ClaimComprehensiveResultDTO> getClaim(@PathVariable final Long id) {
        log.debug("REST request to get an invoice : {}", id);
        return ResponseEntity.ok(service.getOneDto(id));

    }


    @GetMapping("claims/{page}/{size}")
    public ResponseEntity<Page<ClaimComprehensiveResultDTO>> findAllClaims(@PathVariable("page") int page, @PathVariable("size") int size,
                                                                           @RequestParam(required = false) String status) {
        if (status == null)
            return ResponseEntity.ok(service.findAll(PageRequest.of(page, size)));
        else {
            return ResponseEntity.ok(service.findAllByStatus(PageRequest.of(page, size), status));
        }

    }

}
