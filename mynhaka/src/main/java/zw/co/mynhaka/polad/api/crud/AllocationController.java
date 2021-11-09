package zw.co.mynhaka.polad.api.crud;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zw.co.mynhaka.polad.domain.dtos.allocation.PolicyResponse;
import zw.co.mynhaka.polad.service.iface.AllocationService;
import zw.co.mynhaka.polad.service.iface.PaymentService;

@RestController
@RequestMapping("/api/v1/allocation-payment")
public class AllocationController {

    private final AllocationService allocationService;
    private final PaymentService paymentService;

    public AllocationController(final AllocationService allocationService, final PaymentService paymentService) {
        this.allocationService = allocationService;
        this.paymentService = paymentService;
    }

    /*

    @PostMapping("/accident-policy")
    ResponseEntity<AllocationResultDTO> allocateToAccidentPolicy(@RequestBody @Valid AllocationCreateDTO allocationCreateDTO) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri)
                .body(allocationService.allocateToAccidentPolicy(allocationCreateDTO));
    }

    @PostMapping("/comprehensive-policy")
    ResponseEntity<AllocationResultDTO> allocateToComprehensivePolicy(@RequestBody @Valid AllocationCreateDTO allocationCreateDTO) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri)
                .body(allocationService.allocateToComprehensivePolicy(allocationCreateDTO));
    }

    @PostMapping("/savings-policy")
    ResponseEntity<AllocationResultDTO> allocateToSavingsPolicy(@RequestBody @Valid AllocationCreateDTO allocationCreateDTO) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri)
                .body(allocationService.allocateToSavingsPolicy(allocationCreateDTO));
    }

    @PostMapping("/funeral-policy")
    ResponseEntity<AllocationResultDTO> allocateToFuneralPolicy(@RequestBody @Valid AllocationCreateDTO allocationCreateDTO) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri)
                .body(allocationService.allocateToFuneralPolicy(allocationCreateDTO));
    }
    */


    @GetMapping("/payment/{id}")
    ResponseEntity<PolicyResponse> getPoliciesUnderPayment(@PathVariable("id") Long id) {


        //return ResponseEntity.ok(paymentService.getPaymentAllocation(id));
        return null;
    }

}
