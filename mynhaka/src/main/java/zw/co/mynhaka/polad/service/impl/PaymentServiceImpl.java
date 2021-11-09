package zw.co.mynhaka.polad.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.mynhaka.polad.clients.FileStorageServiceClient;
import zw.co.mynhaka.polad.domain.dtos.allocation.PolicyResponse;
import zw.co.mynhaka.polad.domain.dtos.payment.PaymentResultDTO;
import zw.co.mynhaka.polad.domain.enums.*;
import zw.co.mynhaka.polad.domain.model.*;
import zw.co.mynhaka.polad.events.payment.OnCreatePaymentEvent;
import zw.co.mynhaka.polad.repository.*;
import zw.co.mynhaka.polad.service.iface.InvoiceService;
import zw.co.mynhaka.polad.service.iface.PaymentService;
import zw.co.mynhaka.polad.service.iface.PolicyHolderService;
import zw.co.mynhaka.polad.service.exception.BusinessValidationException;
import zw.co.mynhaka.polad.service.iface.PolicyService;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.MessageFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final ApplicationEventPublisher applicationEventPublisher;
    private final FileStorageServiceClient fileStorageServiceClient;
    private final PaymentRepository paymentRepository;
    private final InvoiceService invoiceService;
    private final PolicyHolderService policyHolderService;
    private final PolicyRepository policyRepository;
    private final PolicyService policyService;

    @Value("cash.import.format")
    private String cashImportFormat;



    @Override
    public String makeIndividualPayment(Payment payment) {
        Policy policy = policyService.getOne(payment.getPolicy().getId());
        Invoice invoice = invoiceService.getUnpaidInvoiceByPolicyHolder(policy.getPolicyHolder());
        //Payment payment = paymentMapper.toPayment(individualPaymentCreateDTO, invoice);
        payment.setPolicyNumber(policy.getPolicyNumber());
        payment.setPaymentStatus(PaymentStatus.VALIDATED);
        policy.setPolicyStatus(PolicyStatus.PAID_UP);
        policy.setPolicyState(PolicyState.ACTIVE);

        //suspense Logic for individual payment
        payment.suspense(payment.getBilledAmount(),payment.getReceivedAmount());

        //arrears Logic for individual payment
        payment.arrears(payment.getBilledAmount(),payment.getReceivedAmount());

        Payment savedPayment = paymentRepository.save(payment);
        OnCreatePaymentEvent onCreatePaymentEvent = new OnCreatePaymentEvent(this, savedPayment, policy.getPolicyHolder(), "validated", invoice);
        applicationEventPublisher.publishEvent(onCreatePaymentEvent);
        return "Individual Payment has been successfully processed.";
    }



    @Override
    @Transactional
    public void bulkUploadPayments(String filename) {
        Resource resource = fileStorageServiceClient.downloadFile(filename);
        try {
            File file = resource.getFile();

            if (!file.getName().endsWith(".csv")) {
                throw new BusinessValidationException("Unsupported file format");
            }

            MessageFormat format = new MessageFormat(cashImportFormat);

            List<OnCreatePaymentEvent> events = new ArrayList<>();

            Files.lines(file.toPath()).skip(1)
                    .forEach(line -> {
                        try {
                            Object[] values = format.parse(line);

                            LocalDate datePaid = LocalDate.parse((String) values[0]);
                            String policyNumber = (String) values[1];
                            double billedAmount = (Double.parseDouble((String) values[2]));
                            double receivedAmount = (Double.parseDouble((String) values[2]));
                            double suspenseAmount = (Double.parseDouble((String) values[2]));
                            double arrearsAmount = (Double.parseDouble((String) values[2]));

                            String receiptNumber = (String) values[3];

                            Policy policy = (Policy) policyRepository.findByPolicyNumber(policyNumber)
                                    .orElseThrow(() -> new EntityNotFoundException("Policy with policy number " + policyNumber + "not found"));

                            PolicyHolder policyHolder = policy.getPolicyHolder();

                            Invoice invoice = invoiceService.getUnpaidInvoiceByPolicyHolder(policyHolder);

                            Payment payment = Payment.builder()
                                    .paymentDate(datePaid)
                                    .paymentReference(receiptNumber)
                                    .paymentChannel(PaymentChannel.CASH)
                                    .paymentStatus(PaymentStatus.VALIDATED)
                                    .paymentMethod(PaymentMethod.STOP_ORDER)
                                    .baseCurrency(BaseCurrency.ZWL)
                                    .policyNumber(policyNumber)
                                    .billedAmount(billedAmount)
                                    .receivedAmount(receivedAmount)
                                    .suspenseAmount(suspenseAmount)
                                    .arrearsAmount(arrearsAmount)
                                    .invoice(invoice)
                                    .build();


                            Payment savedPayment = paymentRepository.save(payment);
                            events.add(new OnCreatePaymentEvent(this, savedPayment, policyHolder, "validated", invoice));

                        } catch (Throwable e) {
                            throw new BusinessValidationException("Failed to parse values " + e.getMessage());
                        }
                    });

            events.forEach(applicationEventPublisher::publishEvent);

        } catch (IOException e) {
            throw new BusinessValidationException("Failed to load file");
        }
    }

    @Override
    public String initiatePayment(Payment payment) {
        Policy policy = policyService.getOne(payment.getPolicy().getId());

        if(policy==null){
            throw new EntityNotFoundException("Policy not found!");
        } else
        payment.setPaymentStatus(PaymentStatus.INITIATED);
        policy.setPolicyState(PolicyState.ACTIVE);
        policy.setFirstPaymentDate(payment.getPaymentDate());

        //suspense Logic
        payment.suspense(payment.getBilledAmount(),payment.getReceivedAmount());

        //arrears Logic
        payment.arrears(payment.getBilledAmount(),payment.getReceivedAmount());

        paymentRepository.save(payment);
        return "Payment has been successfully initiated.";
    }

    public String update(Payment payment) {
        Optional <Payment> paymentFromDatabase = paymentRepository.findById(payment.getId());
        if(!paymentFromDatabase.isPresent()) throw new EntityNotFoundException("Payment does not exist!");
        //carry date of update
        payment.setPaymentDate(paymentFromDatabase.get().getPaymentDate());

        paymentRepository.save(payment);
        return "Payment has been successfully updated";
    }
    @Override
    public String approvePayment(Long id) {
        Payment payment = getOne(id);
        if (payment.getPaymentStatus() == PaymentStatus.INITIATED) {
            payment.setPaymentStatus(PaymentStatus.VALIDATED);

           /* if (payment.getAmount() >= 0) {
                payment.getInvoice().setInvoiceStatus(InvoiceStatus.PAID);
            } else {
                payment.getInvoice().setInvoiceStatus(InvoiceStatus.PARTIALLY_PAID);
            }
            Employer employer = payment.getInvoice().getEmployer();
            PolicyHolder policyHolder = payment.getInvoice().getPolicyHolder();
            if (employer != null) {
                employer.setBalance(employer.getBalance().subtract(payment.getAmount()));
            } else {
                policyHolder.setBalance(policyHolder.getBalance().subtract(payment.getAmount()));
            }

            */
             paymentRepository.save(payment);

            return "Payment with Id:"+id +" has been successfully approved";

            //  OnCreatePaymentEvent createPaymentEvent = new OnCreatePaymentEvent(this, payment, policyHolder, "validated", payment.getInvoice());
          //  applicationEventPublisher.publishEvent(createPaymentEvent);
            //return paymentMapperon.toPaymentResultDto(payment1);
            //Create Payment Event

        } else {
            throw new BusinessValidationException("Payment with id: " + id + " state is " + payment.getPaymentStatus() + "  and cannot be validated.");
        }
    }

    @Override
    public String cancelPaymentInitiated(Long id) {
        Payment payment = paymentRepository.getOne(id);
        if (payment.getPaymentStatus() == PaymentStatus.INITIATED) {
            payment.setPaymentStatus(PaymentStatus.CANCELLED);
             paymentRepository.save(payment);
            return "Payment with Id:"+id +" has been successfully cancelled";

        } else {
            throw new BusinessValidationException("Payment with id: " + id + " state is " + payment.getPaymentStatus() + "  and cannot be cancelled. Consider reversing the payment");
        }
    }

    /**
     * Reverse Payment--cancells allocation
     * reinstates balance
     * changes invoice status to unpaid
     *
     * @param id
     * @return PaymentResultDTO
     */
    @Override
    public String reversePayment(Long id) {
        Payment payment = paymentRepository.getOne(id);
        if (payment.getPaymentStatus() == PaymentStatus.VALIDATED) {
           payment.getInvoice().setInvoiceStatus(InvoiceStatus.NEW);
            payment.setPaymentStatus(PaymentStatus.REVERSED);
           Employer employer = payment.getInvoice().getEmployer();
            PolicyHolder policyHolder = payment.getInvoice().getPolicyHolder();
            if (employer != null) {
                employer.setBalance(employer.getBalance() + payment.getBilledAmount());
            } else {
                //policyHolder.setBalance(policyHolder.getBalance() + payment.getBilledAmount());
            }
            payment.arrears(0.0,0.0);
            payment.suspense(0.0,0.0) ;
            payment.getInvoice().setInvoiceStatus(InvoiceStatus.NEW);


             paymentRepository.save(payment);

           return "Payment with Id:"+id +" has been successfully reversed";
        } else {
            throw new BusinessValidationException("Payment with id: " + id + " state is " + payment.getPaymentStatus() + "  and cannot be reversed. Consider cancelling the payment");

        }
    }

    @Override
    public Page<Payment> findAllByStatus(String paymentStatus, Pageable pageable) {
        return paymentRepository.findAllByPaymentStatus(PaymentStatus.valueOf(paymentStatus), pageable);
    }

    @Override
    public List<Payment> findAllByStatus(String paymentStatus) {
        return paymentRepository.findAllByPaymentStatus(PaymentStatus.valueOf(paymentStatus));
                /*.stream()
                .map(paymentMapper::toPaymentResultDto)
                .collect(Collectors.toList());

                 */

    }



    @Override
    public Page<Payment> findAll(Pageable pageable) {
        return paymentRepository.findAll(pageable);
    }

    @Override
    public List<Payment> findAll() {
        List<Payment> payments = paymentRepository.findAll();
        if (payments.isEmpty()){
            throw  new EntityNotFoundException("No payments Available at the moment");

        } else
        return payments;
                /*.stream()
                .map(paymentMapper::toPaymentResultDto)
                .collect(Collectors.toList());

                 */
    }

    @Override
    public Optional<Payment> findOne(Long id) {
        return paymentRepository.findById(id);
    }


    @Override
    public Payment getOne(Long id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        if (!payment.isPresent()){
            throw  new EntityNotFoundException("Payment with Id not found!");
        }else
        return payment.get();
    }

    @Override
    public PaymentResultDTO getOneDto(Long id) {
        return paymentRepository.findById(id)
                .map(payment -> new PaymentResultDTO())
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void delete(Long id) {
        paymentRepository.deleteById(id);
    }

    @Override
    public Payment findByIdAndPaymentStatus(Long paymentId, String statuss) {
        PaymentStatus status = PaymentStatus.VALIDATED;
        return paymentRepository.findByIdAndPaymentStatus(paymentId, status);
    }

    @Override
    public Page<Payment> getPaymentsForPolicyHolder(Long id, Pageable pageable) {
        PolicyHolder policyHolder = policyHolderService.getOne(id);
        return paymentRepository.findAllByPolicyHolderOrderByCreatedDate(policyHolder, pageable);
    }

    /*@Override
    public List<Payment> getPaymentsForPolicy(Long id) {
        Policy policy = policyService.getOne(id);
        return paymentRepository.findAllByPolicyOrderByCreatedDate(policy);
                /*.stream()
                .map(paymentMapper::toPaymentResultDto)
                .collect(Collectors.toList());


    }

     */


    @Override
    public List<Payment> findAllByPolicyHolder(PolicyHolder policyHolder) {
        List<Payment> policyPayments = paymentRepository.findAllByPolicyHolder(policyHolder);
        if(policyPayments.isEmpty()){
            throw  new EntityNotFoundException("Payment history for Policy Holder : " + policyHolder.getFirstname() + " " + policyHolder.getLastname() + " is not available.");

        }
        return policyPayments;
    }

    @Override
    public List<PolicyResponse> getPaymentAllocation(Long id) {
        List<PolicyResponse> policyResponseList = new ArrayList<>();
        Payment payment = paymentRepository.getOne(id);
        Invoice invoice = payment.getInvoice();

        for (int i = 0;i<invoice.getInvoiceItemSet().size();i++){

        }

        return policyResponseList;
    }
}
