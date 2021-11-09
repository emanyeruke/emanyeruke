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
import zw.co.mynhaka.polad.domain.dtos.funeral.ClaimFuneralResultDTO;
import zw.co.mynhaka.polad.domain.model.ClaimFuneral;
import zw.co.mynhaka.polad.service.iface.ClaimFuneralService;

import java.net.URI;


@Slf4j
@RestController
@RequestMapping(value = "/api/v1/funeral-policy/claim", produces = MediaType.APPLICATION_JSON_VALUE)
public class FuneralClaimOperations {

    private final ClaimFuneralService service;
    private final ModelMapper modelMapper;

    @Autowired
    public FuneralClaimOperations(ClaimFuneralService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @PostMapping("claim")
    ApiResponse registerClaim(@RequestBody DeathClaimCreateDto deathClaimCreateDto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        ClaimFuneral claimFuneral = modelMapper.map(deathClaimCreateDto,ClaimFuneral.class);
        return new ApiResponse(200,"SUCCESS",service.add(claimFuneral));
    }


    @PutMapping("claim/refund")
    ResponseEntity<ClaimFuneralResultDTO> refundPolicy(@PathVariable final Long id) {
        return null;
    }


    @PutMapping("claim/cancel/{id}")
    public ApiResponse cancelInitiatedClaim(@PathVariable("id") final Long id) {
        log.debug("REST request te cancel funeral claim : {}", id);
        return new ApiResponse(200,"SUCCESS",service.cancelClaim(id));

    }

    @PutMapping("claim/validate/{id}")
    public ApiResponse validateClaim(@PathVariable("id") Long id) {
        log.debug("REST request to validate a claim : {}", id);
        return new ApiResponse(200,"SUCCESS",service.validateClaim(id));
    }

    @PutMapping("claim/initiate-pay/{id}")
    public ApiResponse initiatePay(@PathVariable("id") Long id) {
        log.debug("REST request to initiate claim payment : {}", id);
        return new ApiResponse(200,"SUCCESS",service.payClaim(id));
    }

    @PutMapping("claim/authorize/{id}")
    public ApiResponse authorizeClaim(@PathVariable  Long id) {
        log.debug("REST request to authorize funeral claim : {}", id);
        return new ApiResponse(200,"SUCCESS",service.authorizeClaim(id));
    }

    @PutMapping("claim/approve/{id}")
    public ApiResponse approveClaim(@PathVariable  Long id) {
        log.debug("REST request to approve funeral claim : {}", id);
        return new ApiResponse(200,"SUCCESS",service.approveClaim(id));
    }

    @GetMapping("claim/{id}")
    public ResponseEntity<ClaimFuneralResultDTO> getClaim(@PathVariable final Long id) {
        log.debug("REST request to get an invoice : {}", id);
        return ResponseEntity.ok(service.getOneDto(id));

    }


    @GetMapping("claims/{page}/{size}")
    public ResponseEntity<Page<ClaimFuneralResultDTO>> findAllClaims(@PathVariable("page") int page,
                                                                     @PathVariable("size") int size,
                                                                     @RequestParam(required = false) String status) {
        if (status == null)
            return ResponseEntity.ok(service.findAll(PageRequest.of(page, size)));
        else {
            return ResponseEntity.ok(service.findAllByStatus(PageRequest.of(page, size), status));
        }
    }
}
