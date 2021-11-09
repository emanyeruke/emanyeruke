package zw.co.mynhaka.polad.api.crud;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import zw.co.mynhaka.polad.api.response.ApiResponse;
import zw.co.mynhaka.polad.domain.dtos.commissionpayment.CommissionPaymentCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.commissionpayment.CommissionPaymentResultDTO;
import zw.co.mynhaka.polad.domain.dtos.commissionpayment.CommissionPaymentUpdateDto;
import zw.co.mynhaka.polad.domain.enums.PolicyUpgradeStatus;
import zw.co.mynhaka.polad.domain.model.*;
import zw.co.mynhaka.polad.service.exception.BusinessValidationException;
import zw.co.mynhaka.polad.service.iface.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommissionPaymentController {

    private final CommissionPaymentService commissionPaymentService;
    private final ModelMapper modelMapper;
    private final CommissionService commissionService;
    private final AgentService agentService;
    private final PaymentService paymentService;
    private final ManagerService managerService;



    @GetMapping("/commission-payments")
    public ApiResponse findAllCommissionPayments() {
        return new ApiResponse(200,"SUCCESS", commissionPaymentService.findAll());
    }

    @GetMapping("/commission-payment/{id}")
    public ApiResponse getCommissionPayment(@PathVariable ("id") Long id) {
        return new ApiResponse(200, "SUCCESS",commissionPaymentService.getOne(id));
    }

    @PostMapping("/commission-payment/initiate")
    public ApiResponse initiatePayment(@RequestBody @Valid CommissionPaymentCreateDTO paymentCreateDTO) {

        CommissionPayment commissionPayment = modelMapper.map(paymentCreateDTO,CommissionPayment.class);

        //get mapped classes Ids
        Commission commission = commissionService.getOne(paymentCreateDTO.getCommissionId());
        Payment payment = paymentService.getOne(paymentCreateDTO.getPaymentId());
        Agent agent = agentService.getOne(paymentCreateDTO.getAgentId());
        Manager manager = managerService.getOne(paymentCreateDTO.getManagerId());


        //set the Mapped classes Ids
        commissionPayment.setCommission(commission);
        commissionPayment.setPayment(payment);
        commissionPayment.setAgent(agent);
        commissionPayment.setManager(manager);


        return new ApiResponse(200,"SUCCESS", commissionPaymentService.initiatePayment(commissionPayment));
    }
    @PutMapping("/upsell/initiate/{commission-payment-id}/{upsell-agent}/{upsell-manager}")
    public ApiResponse initiateUpsellCommissionPayment(//@RequestBody @Valid CommissionPaymentUpdateDto commissionPaymentUpdateDto,
                                     @PathVariable("commission-payment-id") Long commissionPaymentId,
                                      @PathVariable ("upsell-agent")  Long upsellAgentId,
                                       @PathVariable  ("upsell-manager") Long upsellManagerId) {

        return new ApiResponse(200,"SUCCESS", commissionPaymentService.initiateUpsellPayment(commissionPaymentId,upsellAgentId,upsellManagerId));
    }

    @PutMapping("/commission-payment/update/{commission-payment-id}")
    public ApiResponse updateCommissionPayment(@RequestBody @Valid CommissionPaymentUpdateDto commissionPaymentUpdateDto,
                                     @PathVariable("commission-payment-id") Long commissionPaymentId) {
        CommissionPayment commissionPayment = modelMapper.map(commissionPaymentUpdateDto,CommissionPayment.class);

        //Update old record
        Payment oldRecord = paymentService.getOne(commissionPaymentId);

        return new ApiResponse(200,"SUCCESS", commissionPaymentService.update(commissionPayment));
    }
    @GetMapping("get-all")
    public Page<CommissionPaymentResultDTO> getAllCommissionPayments(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size
    ) {
        return commissionPaymentService.getAllCommissionPayments(page, size);
    }

    @GetMapping("commission-payments/{status}")
    public Page<CommissionPaymentResultDTO> getAllCommissionPaymentsByStatus(
            @PathVariable("status") String status,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size
    ) {
        return commissionPaymentService.getAllCommissionPaymentsByStatus(status, page, size);
    }
}

