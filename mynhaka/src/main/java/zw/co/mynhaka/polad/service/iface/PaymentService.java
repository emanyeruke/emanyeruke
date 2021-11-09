package zw.co.mynhaka.polad.service.iface;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import zw.co.mynhaka.polad.domain.dtos.allocation.PolicyResponse;
import zw.co.mynhaka.polad.domain.dtos.payment.IndividualPaymentCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.payment.PaymentResultDTO;
import zw.co.mynhaka.polad.domain.model.Payment;
import zw.co.mynhaka.polad.domain.model.Policy;
import zw.co.mynhaka.polad.domain.model.PolicyHolder;

import java.util.List;
import java.util.Optional;

public interface PaymentService {

    String initiatePayment(Payment payment);

    String approvePayment(Long id);

    String cancelPaymentInitiated(Long id);

    String reversePayment(Long id);

    void bulkUploadPayments(String filename);

    Page<Payment> findAllByStatus(final String paymentStatus, final Pageable pageable);

    List<Payment> findAllByStatus(final String paymentStatus);

    String update (Payment payment);

    Page<Payment> findAll(final Pageable pageable);

    List<Payment> findAll();

    Optional<Payment> findOne(final Long id);

    Payment getOne(final Long id);

    PaymentResultDTO getOneDto(final Long id);

    void delete(final Long id);

    Payment findByIdAndPaymentStatus(Long paymentId, String toString);

    Page<Payment> getPaymentsForPolicyHolder(Long id, Pageable pageable);

   // List<Payment> getPaymentsForPolicy(Long id);

    List<Payment> findAllByPolicyHolder(PolicyHolder policyHolder);

    List<PolicyResponse> getPaymentAllocation(Long id);

    String makeIndividualPayment(Payment payment);
}
