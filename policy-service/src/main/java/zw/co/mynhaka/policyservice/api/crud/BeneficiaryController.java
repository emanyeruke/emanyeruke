package zw.co.mynhaka.policyservice.api.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import zw.co.mynhaka.policyservice.audit.Audit;
import zw.co.mynhaka.policyservice.domain.dto.beneficiary.BeneficiaryCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.beneficiary.BeneficiaryResultDTO;
import zw.co.mynhaka.policyservice.service.BeneficiaryService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/beneficiary", produces = MediaType.APPLICATION_JSON_VALUE)
public class BeneficiaryController {

    private final BeneficiaryService beneficiaryService;

    @PostMapping("add-beneficiary/{policyNumber}")
    @Audit(resource = "Beneficiary", action = "Create beneficiary")
    public ResponseEntity<BeneficiaryResultDTO> addBeneficiary(
            @PathVariable("policyNumber") String policyNumber,
            @RequestBody @Valid BeneficiaryCreateDTO beneficiaryCreateDTO
    ) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri)
                .body(beneficiaryService.save(policyNumber, beneficiaryCreateDTO));
    }

    @PostMapping("add-beneficiaries/{policyNumber}")
    @Audit(resource = "Beneficiary", action = "Create Beneficiary")
    public ResponseEntity<List<BeneficiaryResultDTO>> addBeneficiary(
            @PathVariable("policyNumber") String policyNumber,
            @RequestBody @Valid List<BeneficiaryCreateDTO> beneficiaryCreateDTOList
    ) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri)
                .body(beneficiaryService.save(policyNumber, beneficiaryCreateDTOList));
    }

    @GetMapping("get-beneficiary/{id}")
    @Audit(resource = "Beneficiary", action = "View Beneficiary")
    public ResponseEntity<BeneficiaryResultDTO> getBeneficiary(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(beneficiaryService.findById(id));
    }

    @GetMapping("get-all-beneficiaries/policy/{policyNumber}")
    @Audit(resource = "Beneficiary", action = "View all beneficiaries")
    public ResponseEntity<List<BeneficiaryResultDTO>> getAllBeneficiariesByPolicy (
            @PathVariable("policyNumber") String policyNumber
    ) {
        return ResponseEntity.ok(beneficiaryService.findAllByPolicy(policyNumber));
    }

    @DeleteMapping("delete-beneficiary/{id}")
    @Audit(resource = "Beneficiary", action = "Delete beneficiary")
    public ResponseEntity<?> deleteBeneficiary(
            @PathVariable("id") Long id
    ) {
        beneficiaryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
