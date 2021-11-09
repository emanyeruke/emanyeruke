package zw.co.mynhaka.polad.api.crud;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.mynhaka.polad.api.response.ApiResponse;
import zw.co.mynhaka.polad.domain.dtos.payment.IndividualPaymentCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.payment.PaymentCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.payment.PaymentUpdateDTO;
import zw.co.mynhaka.polad.domain.model.Payment;
import zw.co.mynhaka.polad.domain.model.Policy;
import zw.co.mynhaka.polad.domain.model.PolicyHolder;
import zw.co.mynhaka.polad.service.iface.PaymentService;
import zw.co.mynhaka.polad.service.iface.PolicyHolderService;
import zw.co.mynhaka.polad.service.iface.PolicyService;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class PaymentController {

    private final PaymentService paymentService;
    private final ModelMapper modelMapper;
    private final PolicyService policyService;
    private  final PolicyHolderService policyHolderService;


    @GetMapping("/payments/{page}/{size}")
    public ResponseEntity<Page<Payment>> getAllPayments(@PathVariable("page") int page, @PathVariable("size") int size) {
        return ResponseEntity.ok(paymentService.findAll(PageRequest.of(page, size)));
    }

    @GetMapping("/payments")
    public ApiResponse findAllPayments() {
        return new ApiResponse(200,"SUCCESS", paymentService.findAll());
    }

    @GetMapping("/payment/{id}")
    public ApiResponse getPayment(@PathVariable Long id) {
        log.debug("REST request to get a payment : {}", id);
        return new ApiResponse(200, "SUCCESS",paymentService.getOne(id));
    }

    @PostMapping("/payment/initiate/{policy-id}")
    public ApiResponse initiatePayment(@PathVariable ("policy-id") long id,
                                       @RequestBody @Valid PaymentCreateDTO paymentCreateDTO) {

        Payment payment = modelMapper.map(paymentCreateDTO,Payment.class);

        //PolicyHolder policyHolder = policyHolderService.getOne(payment.getPolicyHolder().getId());

       Policy policy = policyService.getOne(id);



        //set the policyHolder
        payment.setPolicyHolder(policy.getPolicyHolder());

        //set the policy
        payment.setPolicy(policy);

        //get policy number from the policy
        payment.setPolicyNumber(policy.getPolicyNumber());


        return new ApiResponse(200,"SUCCESS", paymentService.initiatePayment(payment));
    }

    @PutMapping("/payment/update/{payment-id}")
    public ApiResponse updatePayment(@RequestBody @Valid PaymentUpdateDTO paymentUpdateDTO,
                                        @PathVariable("payment-id") Long paymentId) {
        Payment payment = modelMapper.map(paymentUpdateDTO,Payment.class);

        //Update old record
        Payment oldRecord = paymentService.getOne(paymentId);

        //get policy number from old record
        payment.setPolicyNumber(oldRecord.getPolicyNumber());

        return new ApiResponse(200,"SUCCESS", paymentService.update(payment));
    }

    @GetMapping("/payment-history/{policy-holder-id}")
    public ApiResponse getPaymentHistoryOfPolicy(@PathVariable("policy-holder-id") Long policyHolderId){
        PolicyHolder policyHolder = policyHolderService.getOne(policyHolderId);

        return new ApiResponse(200, "SUCCESS", paymentService.findAllByPolicyHolder(policyHolder));
    }
    @GetMapping("payments/status/{status}/{page}/{size}")
    public ApiResponse findAllPaymentsByStatus(@PathVariable("status") String status, @PathVariable("page") int page, @PathVariable("size") int size) {
        return new ApiResponse(200, "SUCCESS", paymentService.findAllByStatus(status,PageRequest.of(page, size)));
    }

    @GetMapping("payments/status/{status}")
    public ApiResponse findAllPaymentsByStatus(@PathVariable("status") String status) {
        return new ApiResponse(200, "SUCCESS", paymentService.findAllByStatus(status));
    }

    @PutMapping("payment/initiate/cancel/{id}")
    public ApiResponse cancelInitiatedPayment(@PathVariable("id")  Long id) {
        log.debug("REST request to get an invoice : {}", id);
        return new ApiResponse(200,"SUCCESS",paymentService.cancelPaymentInitiated(id));
    }

    @PutMapping("/payment/validate/{id}")
    public ApiResponse validatePayment(@PathVariable Long id) {
        log.debug("REST request to generate an invoice : {}", id);
        return new ApiResponse(200,"SUCCESS",paymentService.approvePayment(id));
    }


    @PutMapping("/payment/reverse{id}")
    public ApiResponse reversePayment(@PathVariable("id")  Long id) {
        return new ApiResponse(200,"SUCCESS",paymentService.reversePayment(id));
    }


    @GetMapping("/payment/policyholder/{id}/{page}/{size}")
    public ApiResponse getPaymentForPolicyHolder(@PathVariable("id")  Long id, @PathVariable("page") int page, @PathVariable("size") int size) {
        log.debug("REST request to get payments for policyholder : {}", id);
        return new ApiResponse(200,"SUCCESS",paymentService.getPaymentsForPolicyHolder(id,PageRequest.of(page, size)));

    }

    /*(@GetMapping("/payment/policyholder/{id}")
    public ApiResponse getPaymentForPolicyHolder(@PathVariable("id") final Long id) {
        log.debug("REST request to get payments for policyholder : {}", id);
        return new ApiResponse(200,"SUCCESS",paymentService.getPaymentsForPolicy(id));
    }

     */

    @PostMapping("/payment/make-payment/{policy-id}")
    public ApiResponse makeIndividualPayment(@Valid @RequestBody IndividualPaymentCreateDTO individualPaymentCreateDTO,
                                             @PathVariable("policy-id")  long id) {

        Payment payment = modelMapper.map(individualPaymentCreateDTO,Payment.class);
       // PolicyHolder policyHolder = policyHolderService.getOne(payment.getPolicyHolder().getId());

        Policy policy = policyService.getOne(id);

        //set policy holder from policy
        payment.setPolicyHolder(policy.getPolicyHolder());

        //set the policy
        payment.setPolicy(policy);

        //get policy number from the policy
        payment.setPolicyNumber(policy.getPolicyNumber());

        return new ApiResponse(200,"SUCCESS",paymentService.makeIndividualPayment(payment));
    }

//TODO: Update individual payment end point

    @PostMapping("/payment/cash-import/{filename}")
    public ResponseEntity uploadIndividualPayments(
            @PathVariable("filename") String filename
    ) {
        paymentService.bulkUploadPayments(filename);
        return ResponseEntity.ok().build();
    }
}
