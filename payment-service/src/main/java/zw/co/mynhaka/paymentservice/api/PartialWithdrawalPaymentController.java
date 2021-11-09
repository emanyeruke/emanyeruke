package zw.co.mynhaka.paymentservice.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import zw.co.mynhaka.paymentservice.audit.Audit;
import zw.co.mynhaka.paymentservice.domain.dto.payment.PartialWithdrawalPaymentCreateDTO;
import zw.co.mynhaka.paymentservice.domain.dto.payment.PartialWithdrawalPaymentResultDTO;
import zw.co.mynhaka.paymentservice.service.PartialWithdrawalPaymentService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/partial-withdrawal", produces = MediaType.APPLICATION_JSON_VALUE)
public class PartialWithdrawalPaymentController {

    private final PartialWithdrawalPaymentService partialWithdrawalPaymentService;

    @PostMapping("payment")
    @Audit(resource = "Payment", action = "Request Partial Withdrawal")
    public ResponseEntity<PartialWithdrawalPaymentResultDTO> create(
            @RequestBody @Valid PartialWithdrawalPaymentCreateDTO partialWithdrawalPaymentCreateDTO
    ) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri)
                .body(partialWithdrawalPaymentService.save(partialWithdrawalPaymentCreateDTO));
    }

    @PostMapping("payments")
    @Audit(resource = "Payment", action = "Request Partial Withdrawal")
    public ResponseEntity<List<PartialWithdrawalPaymentResultDTO>> create(
            @RequestBody @Valid List<PartialWithdrawalPaymentCreateDTO> partialWithdrawalPaymentCreateDTOList
    ) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri)
                .body(partialWithdrawalPaymentService.save(partialWithdrawalPaymentCreateDTOList));
    }

    @GetMapping("get-all-payments/{policyNumber}")
    @Audit(resource = "Payment", action = "Get All Payments")
    public ResponseEntity<List<PartialWithdrawalPaymentResultDTO>> getAllPayments(
            @PathVariable("policyNumber") String policyNumber
    ) {
        return ResponseEntity.ok(partialWithdrawalPaymentService.findAllByPolicyNumber(policyNumber));
    }
}
