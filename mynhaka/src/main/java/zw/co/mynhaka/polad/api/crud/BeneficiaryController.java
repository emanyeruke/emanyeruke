package zw.co.mynhaka.polad.api.crud;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import zw.co.mynhaka.polad.api.response.ApiResponse;
import zw.co.mynhaka.polad.domain.dtos.BeneficiaryCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.BeneficiaryResultDTO;
import zw.co.mynhaka.polad.domain.dtos.BeneficiaryUpdateDTO;
import zw.co.mynhaka.polad.domain.model.Beneficiary;
import zw.co.mynhaka.polad.service.iface.BeneficiaryService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/beneficiaries", produces = MediaType.APPLICATION_JSON_VALUE)
public class BeneficiaryController {

    private final BeneficiaryService beneficiaryService;

    @PostMapping("{policyNumber}")
    public ResponseEntity<List<BeneficiaryResultDTO>> create(
            @PathVariable("policyNumber") String policyNumber,
            @RequestBody @Valid List<BeneficiaryCreateDTO> beneficiaryCreateDTOList) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri)
                .body(beneficiaryService.save(policyNumber, beneficiaryCreateDTOList));
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<BeneficiaryResultDTO> update(
            @PathVariable("id") Long id,
            @RequestBody @Valid BeneficiaryUpdateDTO beneficiaryUpdateDTO
    ) {
        Beneficiary beneficiary = beneficiaryService.getOne(id);
        
        return ResponseEntity.ok(beneficiaryService.update(id, beneficiaryUpdateDTO));
    }

    @GetMapping("get-all")
    public ApiResponse findAll() {
        return new ApiResponse(200,"SUCCESS",beneficiaryService.findAll());
    }

    @GetMapping("{policyNumber}")
    public ResponseEntity<List<BeneficiaryResultDTO>> getAll(
            @PathVariable("policyNumber") String policyNumber
    ) {
        return ResponseEntity.ok(beneficiaryService.findAllBeneficiariesByPolicy(policyNumber));
    }

    @GetMapping("/one/{policyNumber}")
    public ApiResponse getOneByPolicyNumber(@PathVariable("policyNumber") String policyNumber) {
        return new ApiResponse(200,"SUCCESS",beneficiaryService.findByPolicyNumber(policyNumber));
    }

    @GetMapping("{policyNumber}/dependencies")
    public ResponseEntity<List<BeneficiaryResultDTO>> getAllDependents(
            @PathVariable("policyNumber") String policyNumber
    ) {
        return ResponseEntity.ok(beneficiaryService.findAllDependenciesByPolicy(policyNumber));
    }

    @GetMapping("{policyNumber}/spouses")
    public ResponseEntity<List<BeneficiaryResultDTO>> getAllSpouses(
            @PathVariable("policyNumber") String policyNumber
    ) {
        return ResponseEntity.ok(beneficiaryService.findAllSpousesByPolicy(policyNumber));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") Long id
    ) {
        beneficiaryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
