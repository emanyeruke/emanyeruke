package zw.co.mynhaka.polad.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.polad.domain.dtos.commissionpayment.CommissionPaymentResultDTO;
import zw.co.mynhaka.polad.domain.enums.PaymentStatus;
import zw.co.mynhaka.polad.domain.enums.PolicyType;
import zw.co.mynhaka.polad.domain.enums.PolicyUpgradeStatus;
import zw.co.mynhaka.polad.domain.model.*;
import zw.co.mynhaka.polad.repository.CommissionPaymentRepository;
import zw.co.mynhaka.polad.service.exception.BusinessValidationException;
import zw.co.mynhaka.polad.service.iface.*;
import zw.co.mynhaka.polad.service.mapper.CommissionPaymentMapper;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommissionPaymentServiceImpl implements CommissionPaymentService {

    private final CommissionPaymentRepository commissionPaymentRepository;
    private final CommissionService commissionService;
    private final CommissionPaymentMapper commissionPaymentMapper;
    private final AgentService agentService;
    private final PaymentService paymentService;
    private final ManagerService managerService;

    @Override
    public Page<CommissionPaymentResultDTO> getAllCommissionPayments(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        Page<CommissionPayment> commissionPayments = commissionPaymentRepository.findAll(pageRequest);

        List<CommissionPaymentResultDTO> paymentResultDTOS = commissionPayments.getContent().stream()
                .map(commissionPaymentMapper::toCommissionPaymentResultDTO)
                .collect(Collectors.toList());


        return new PageImpl<>(paymentResultDTOS, pageRequest, commissionPayments.getTotalElements());
    }

    @Override
    public Page<CommissionPaymentResultDTO> getAllCommissionPaymentsByStatus(String paymentStatus, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        Page<CommissionPayment> commissionPayments = commissionPaymentRepository.findAllByPaymentStatus(paymentStatus, pageRequest);

        List<CommissionPaymentResultDTO> paymentResultDTOS = commissionPayments.getContent().stream()
                .map(commissionPaymentMapper::toCommissionPaymentResultDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(paymentResultDTOS, pageRequest, commissionPayments.getTotalElements());
    }

    @Override
    public String initiatePayment(CommissionPayment commissionPayment) {
    Commission commission = commissionService.getOne(commissionPayment.getCommission().getId());
    Payment payment = paymentService.getOne(commissionPayment.getPayment().getId());
    Agent agent = agentService.getOne(commissionPayment.getAgent().getId());
    Manager manager = managerService.getOne(commissionPayment.getManager().getId());

    //validators for Commission payment Processing
    if (commission==null){
        throw  new EntityNotFoundException("Commission Rates for selected policy do not exist.");
    }

        if (payment==null){
            throw  new EntityNotFoundException("Payment for selected policy do not exist.");

        }
        if (payment.getPaymentStatus() != PaymentStatus.VALIDATED){
            throw  new BusinessValidationException("Cannot Process Payment if Payment is not Validated.");
        }

        if (agent==null){
            throw  new EntityNotFoundException("Agent for selected policy do not exist.");
        }

        if (manager==null){
            throw  new EntityNotFoundException("Agent Manager for selected policy do not exist.");
        }


        //Logic for allocating the commissions to agent
       commissionPayment.calculateAgentCommission(commission.getTiedAgentCommissionRate());

        //Logic for allocating the commissions to executive agent
        commissionPayment.calculateAgentCommission(commission.getExecutiveAgentCommissionRate());

        //logic for allocating commission to Manager
        commissionPayment.calculateManagerCommission(commission.getTiedUnitLeaderCommissionRate());

        //Logic for allocating the commissions to parent agent
        commissionPayment.calculateAgentCommission(commission.getParentAgentCommissionRate());

        //logic for allocating commission to parent Manager
        commissionPayment.calculateManagerCommission(commission.getParentUnitLeaderCommissionRate());

        //logic for allocating commission to Executive Manager
        commissionPayment.calculateManagerCommission(commission.getExecutiveUnitLeaderCommissionRate());


        //get policy type from commission rates
        commissionPayment.setPolicyType(commission.getPolicyType());
        commissionPayment.setPaymentStatus(PaymentStatus.VALIDATED);

        //set policy Upgrade Status
        commissionPayment.setPolicyUpgradeStatus(payment.getPolicy().getPolicyUpgradeStatus());

         commissionPaymentRepository.save(commissionPayment);

         return "Commission Payment Has been successfully Processed.";

    }

    @Override
    public String initiateUpsellPayment(Long commissionPaymentId, Long upsellAgentId, Long upsellManagerId){
       CommissionPayment commissionPayment = getOne(commissionPaymentId);
        Agent upsellAgent = agentService.getOne(upsellAgentId);
        Manager upsellManager = managerService.getOne(upsellManagerId);

        if (upsellAgent==null){
            throw  new EntityNotFoundException("Upsell Agent for selected policy do not exist.");
        }

         if (upsellManager==null){
            throw  new EntityNotFoundException("Upsell Agent Manager for selected policy do not exist.");
        }

        //upsell logic & validation
        if (commissionPayment.getPayment().getPolicy().getPolicyUpgradeStatus() == PolicyUpgradeStatus.UPGRADED) {
            //logic for allocating commission to upsell agent
            commissionPayment.calculateUpsellAgentCommission(commissionPayment.getCommission().getUpsellAgentCommissionRate());

            //logic for allocating commission to upsell agent
            commissionPayment.calculateUpsellManagerCommission(commissionPayment.getCommission().getUpsellManagerCommissionRate());
        }else {
            throw new BusinessValidationException("Cannot Process Upsell Commission if policy is not upgraded.");
        }

        //set upsell sales army here
         commissionPayment.setUpsellAgent(upsellAgent);
         commissionPayment.setUpsellManager(upsellManager);

        commissionPaymentRepository.save(commissionPayment);

        return "Upsell Commission Payment has been successfully Initiated.";

    }

    @Override
    public String update(CommissionPayment commissionPayment) {

        Optional <CommissionPayment> paymentFromDatabase = commissionPaymentRepository.findById(commissionPayment.getId());
        if(!paymentFromDatabase.isPresent()) throw new EntityNotFoundException("Payment does not exist!");
        //carry date of update
        commissionPayment.setPaymentDate(paymentFromDatabase.get().getPaymentDate());

        commissionPaymentRepository.save(commissionPayment);

        return "Upsell Commission Payment has been successfully updated";
    }

    @Override
    public CommissionPayment getOne(Long id) {
        Optional<CommissionPayment> commissionPayment = commissionPaymentRepository.findById(id);
        if (!commissionPayment.isPresent()){
            throw new EntityNotFoundException("Commission payment does not exist.");
        }
        return commissionPayment.get();
    }

    @Override
    public List<CommissionPayment> findAll() {
        List<CommissionPayment> commissionPayments = commissionPaymentRepository.findAll();
        if (commissionPayments.isEmpty()){
            throw new EntityNotFoundException("No commission payments available at the moment.");
        }
        return commissionPayments;
    }

   /* private void calculateCommission(int month, PolicyType policyType, BigDecimal premium, Payment payment) {
        Commission commission = commissionService.getCommissionByMonthAndPolicyType(month, policyType.name());
        //BigDecimal tiedAgentCommission = premium.multiply(commission.getTiedAgentCommissionRate());
        //BigDecimal tiedUnitLeaderCommission = premium.multiply(commission.getTiedUnitLeaderCommissionRate());

        commission.calculateCommission(commission.getTiedAgentCommissionRate());
        payment.setReceivedAmount(commission.getAmountReceived());
        CommissionPayment agentCommissionPayment = CommissionPayment.builder()
//                .agent(policyHolder.getAgent())
                .payment(payment)
                .policyType(policyType)
             //   .amountReceived(tiedAgentCommission)
                .paymentDate(LocalDateTime.now())
                .paymentStatus(PaymentStatus.INITIATED)
                .build();

        CommissionPayment managerCommissionPayment = CommissionPayment.builder()
//                .manager(policyHolder.getAgent().getManager())
                .payment(payment)
                .policyType(policyType)
              //  .amountReceived(tiedUnitLeaderCommission)
                .paymentDate(LocalDateTime.now())
                .paymentStatus(PaymentStatus.INITIATED)
                .build();

        //commissionPaymentRepository.saveAll(List.of(agentCommissionPayment, managerCommissionPayment));
    }

    */
}
