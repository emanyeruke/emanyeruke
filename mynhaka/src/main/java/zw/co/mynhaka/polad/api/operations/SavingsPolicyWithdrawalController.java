package zw.co.mynhaka.polad.api.operations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import zw.co.mynhaka.polad.audit.Audit;
import zw.co.mynhaka.polad.domain.dtos.SavingsPartialWithdrawalCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.SavingsPartialWithdrawalResultDTO;
import zw.co.mynhaka.polad.domain.dtos.SavingsPartialWithdrawalUpdateDTO;
import zw.co.mynhaka.polad.service.iface.SavingsWithdrawalService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/savings/withdrawals", produces = MediaType.APPLICATION_JSON_VALUE)
public class SavingsPolicyWithdrawalController {

    private final SavingsWithdrawalService savingsWithdrawalService;

    @PostMapping
    @Audit(resource = "Savings Policy", action = "Create partial withdrawal request Savings Policy")
    public ResponseEntity<SavingsPartialWithdrawalResultDTO> create(
            @RequestBody @Valid SavingsPartialWithdrawalCreateDTO savingsPartialWithdrawalCreateDTO
    ) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri)
                .body(savingsWithdrawalService.save(savingsPartialWithdrawalCreateDTO));
    }

    @PutMapping
    @Audit(resource = "Savings Policy", action = "Approve Partial Withdrawal")
    public ResponseEntity<List<SavingsPartialWithdrawalResultDTO>> update(
            @RequestBody @Valid List<SavingsPartialWithdrawalUpdateDTO> savingsPartialWithdrawalUpdateDTOList
    ) {
        return ResponseEntity.ok(savingsWithdrawalService.update(savingsPartialWithdrawalUpdateDTOList));
    }

    @GetMapping("{page}/{size}")
    @Audit(resource = "Savings Policy", action = "Get All Partial withdrawals Savings Policy")
    public ResponseEntity<Page<SavingsPartialWithdrawalResultDTO>> findAll(
            @PathVariable("page") int page,
            @PathVariable("size") int size
    ) {
        return ResponseEntity.ok(savingsWithdrawalService.findAll(page, size));
    }

    @GetMapping("{policyNumber}")
    @Audit(resource = "Savings Policy", action = "Get partial withdrawals Savings Policy")
    public ResponseEntity<List<SavingsPartialWithdrawalResultDTO>> findAll(
            @PathVariable("policyNumber") String policyNumber
    ) {
        return ResponseEntity.ok(savingsWithdrawalService.findAllByPolicyNumber(policyNumber));
    }

    @DeleteMapping("{id}")
    @Audit(resource = "Savings Policy", action = "Delete partial withdrawal Savings Policy")
    public ResponseEntity<?> delete(
            @PathVariable("id") Long id
    ) {
        savingsWithdrawalService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
