package zw.co.mynhaka.polad.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import zw.co.mynhaka.polad.domain.enums.PaymentStatus;
import zw.co.mynhaka.polad.domain.model.Payment;
import zw.co.mynhaka.polad.domain.model.Policy;
import zw.co.mynhaka.polad.domain.model.PolicyHolder;
import zw.co.mynhaka.polad.reports.claims.IPaymentTotal;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Page<Payment> findAllByPaymentStatus(PaymentStatus paymentStatus, Pageable pageable);

    List<Payment> findAllByPaymentStatus(PaymentStatus paymentStatus);

    Payment findByIdAndPaymentStatus(Long id, PaymentStatus status);

    List<Payment> findAllByPolicyHolder(PolicyHolder policyHolder);

    Page<Payment> findAllByPolicyHolderOrderByCreatedDate(PolicyHolder policyHolder, Pageable pageable);

    List<Payment> findAllByPolicyHolderOrderByCreatedDate(PolicyHolder policyHolder);

    @Query(value = "select IFNULL(sum(amount),0) as payment\n" +
            "from payment\n" +
            "group by week(current_date())", nativeQuery = true)
    IPaymentTotal findAmountPaidThisWeek();
}
