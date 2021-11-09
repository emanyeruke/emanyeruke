package zw.co.mynhaka.policyservice.api.operations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import zw.co.mynhaka.policyservice.domain.dto.savingspartialwithdrawal.SavingsPartialWithdrawalCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.savingspartialwithdrawal.SavingsPartialWithdrawalResultDTO;
import zw.co.mynhaka.policyservice.domain.dto.savingspolicy.SavingsPolicyCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.savingspolicy.SavingsPolicyResultDTO;
import zw.co.mynhaka.policyservice.domain.dto.savingssurrender.SavingsSurrenderCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.savingssurrender.SavingsSurrenderResultDTO;
import zw.co.mynhaka.policyservice.service.SavingsPolicyService;
import zw.co.mynhaka.policyservice.service.SavingsSurrenderService;
import zw.co.mynhaka.policyservice.service.SavingsWithdrawalService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/savings-policy", produces = MediaType.APPLICATION_JSON_VALUE)
public class SavingsPolicyOperationsController {

    private final SavingsPolicyService savingsPolicyService;
    private final SavingsSurrenderService savingsSurrenderService;
    private final SavingsWithdrawalService savingsWithdrawalService;

    @PostMapping("allocate-product")

    public ResponseEntity<SavingsPolicyResultDTO> allocateProduct(
            @RequestBody @Valid SavingsPolicyCreateDTO savingsPolicyCreateDTO
    ) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        log.info("##############Request: {}" + savingsPolicyCreateDTO.toString());
        return ResponseEntity.created(uri)
                .body(savingsPolicyService.allocateProduct(savingsPolicyCreateDTO));
    }

    @GetMapping("get-savings-policy/{id}")
    public ResponseEntity<SavingsPolicyResultDTO> getOne (
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(savingsPolicyService.findById(id));
    }

    @GetMapping("get-policy/{policyNumber}")
    public ResponseEntity<SavingsPolicyResultDTO> getByPolicyNumber(
            @PathVariable("policyNumber") String policyNumber
    ) {
        return ResponseEntity.ok(savingsPolicyService.findByPolicyNumber(policyNumber));
    }

    @GetMapping("get-all-savings-policies/{page}/{size}")
    public ResponseEntity<Page<SavingsPolicyResultDTO>> getAll (
            @PathVariable("page") int page,
            @PathVariable("size") int size
    ) {
        return ResponseEntity.ok(savingsPolicyService.findAll(page, size));
    }

    @GetMapping("get-all-savings-policies/policy-holder/{policyHolderId}")
    public ResponseEntity<List<SavingsPolicyResultDTO>> getAllByPolicyHolder (
            @PathVariable("policyHolderId") Long policyHolderId
    ) {
        return ResponseEntity.ok(savingsPolicyService.findByPolicyHolderId(policyHolderId));
    }

    @DeleteMapping("delete-savings-policy/{id}")
    public ResponseEntity<?> deleteSavingsPolicy(
            @PathVariable("id") Long id
    ) {
        savingsPolicyService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("surrender-policy/{policyNumber}")
    public ResponseEntity<SavingsSurrenderResultDTO> surrenderPolicy(
            @PathVariable("policyNumber") String policyNumber,
            @RequestBody @Valid SavingsSurrenderCreateDTO savingsSurrenderCreateDTO
    ) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri)
                .body(savingsSurrenderService.save(policyNumber, savingsSurrenderCreateDTO));
    }

    @PutMapping("approve-surrender/{id}")
    public ResponseEntity<SavingsSurrenderResultDTO> approveSurrender (
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(savingsSurrenderService.approveSurrender(id));
    }

    @PutMapping("approve-surrender-payment/{id}")
    public ResponseEntity<SavingsSurrenderResultDTO> approveSurrenderPayment (
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(savingsSurrenderService.approveSurrenderPayment(id));
    }

    @GetMapping("get-all-surrender/applications/{page}/{size}")
    public ResponseEntity<Page<SavingsSurrenderResultDTO>> getAllApplications (
            @PathVariable("page") int page,
            @PathVariable("size") int size
    ) {
        return ResponseEntity.ok(savingsSurrenderService.findAll(page, size));
    }

    @GetMapping("get-all-surrender/applications/{surrenderStatus}/{page}/{size}")
    public ResponseEntity<Page<SavingsSurrenderResultDTO>> getAllApplications (
            @PathVariable("page") int page,
            @PathVariable("size") int size,
            @PathVariable("surrenderStatus") String surrenderStatus
    ) {
        return ResponseEntity.ok(savingsSurrenderService.findAllByStatus(page, size, surrenderStatus));
    }

    @DeleteMapping("delete-surrender/{id}")
    public ResponseEntity<?> deleteSurrender(
            @PathVariable("id") Long id
    ) {
        savingsSurrenderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("partial-withdrawal/{policyNumber}")
    public ResponseEntity<SavingsPartialWithdrawalResultDTO> partialWithdrawal(
            @PathVariable("policyNumber") String policyNumber,
            @RequestBody @Valid SavingsPartialWithdrawalCreateDTO savingsPartialWithdrawalCreateDTO
    ) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri)
                .body(savingsWithdrawalService.save(policyNumber, savingsPartialWithdrawalCreateDTO));
    }

    @PutMapping("approve-withdrawal/{id}")
    public ResponseEntity<SavingsPartialWithdrawalResultDTO> approveWithdrawal (
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(savingsWithdrawalService.approveWithdrawal(id));
    }

//    @PutMapping("approve-withdrawal-payment/{id}")
//    public ResponseEntity<SavingsPartialWithdrawalResultDTO> approveWithdrawalPayment (
//            @PathVariable("id") Long id
//    ) {
//        return ResponseEntity.ok(savingsWithdrawalService.approveWithdrawalPayment(id));
//    }

    @GetMapping("get-all-withdrawal/applications/{page}/{size}")
    public ResponseEntity<Page<SavingsPartialWithdrawalResultDTO>> getAllWithdrawalApplications (
            @PathVariable("page") int page,
            @PathVariable("size") int size
    ) {
        return ResponseEntity.ok(savingsWithdrawalService.findAll(page, size));
    }

    @GetMapping("get-all-withdrawal/applications")
    public ResponseEntity<List<SavingsPartialWithdrawalResultDTO>> getAllWithdrawalApplications () {
        return ResponseEntity.ok(savingsWithdrawalService.findAll());
    }

    @DeleteMapping("delete-withdrawal/{id}")
    public ResponseEntity<?> deleteWithdrawal(
            @PathVariable("id") Long id
    ) {
        savingsWithdrawalService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
