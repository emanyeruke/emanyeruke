package zw.co.mynhaka.polad.api.operations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import zw.co.mynhaka.polad.api.response.ApiResponse;
import zw.co.mynhaka.polad.domain.dtos.claim.ClaimSavingsDeathCreateDto;
import zw.co.mynhaka.polad.domain.model.ClaimSavingsDeath;
import zw.co.mynhaka.polad.service.iface.ClaimSavingsDeathService;

import java.net.URI;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/savings/claim",produces = MediaType.APPLICATION_JSON_VALUE)
public class SavingsDeathClaimOperations {

    private final ClaimSavingsDeathService service;
    private final ModelMapper modelMapper;

    @Autowired
    public SavingsDeathClaimOperations(ClaimSavingsDeathService service,ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }




    @PostMapping("claim")
    public  ApiResponse registerClaim(@RequestBody ClaimSavingsDeathCreateDto claimSavingsDeathCreateDto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        ClaimSavingsDeath claimSavingsDeath = modelMapper.map(claimSavingsDeathCreateDto,ClaimSavingsDeath.class);
        return new ApiResponse(200,"SUCCESS",service.add(claimSavingsDeath));
    }

    /*@PutMapping("claim/cancel")
    public ApiResponse cancelPolicy(@RequestBody CancelPolicyCreateDTO cancelPolicyCreateDto) {
        ClaimSavingsDeath claimSavingsDeath = modelMapper.map(CancelPolicyCreateDTO,ClaimSavingsDeath.class);

        return new ApiResponse(200,"SUCCESS",service.cancelClaim(claimSavingsDeath.getId()));
    }



    @PutMapping("claim/refund")
    ResponseEntity<ClaimFuneralResultDTO> refundPolicy(@RequestBody RefundPolicyCreateDto refundPolicyCreateDto) {
        return null;
    }

     */


    @PutMapping("claim/cancel/{id}")
    public ApiResponse cancelInitiatedClaim(@PathVariable("id")  Long id) {
        return new ApiResponse(200,"SUCCESS",service.cancelClaim(id));

    }

    @PutMapping("claim/validate/{id}")
    public ApiResponse validateClaim(@PathVariable  Long id) {
        return new ApiResponse(200,"SUCCESS",service.approveClaim(id));
    }

    @PutMapping("claim/authorize/{id}")
    public ApiResponse authorizeClaim(@PathVariable  Long id) {
        return new ApiResponse(200,"SUCCESS",service.authorizeClaim(id));
    }
    @GetMapping("claim/{id}")
    public ApiResponse getClaim(@PathVariable  Long id) {
        return new ApiResponse(200,"SUCCESS",service.getOne(id));

    }

    @GetMapping("/all")
    public ApiResponse getAllClaims() {
        return new ApiResponse(200,"SUCCESS",service.findAll());

    }


}
