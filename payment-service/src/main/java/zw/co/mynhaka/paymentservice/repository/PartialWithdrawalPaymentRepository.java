package zw.co.mynhaka.paymentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.mynhaka.paymentservice.domain.model.PartialWithdrawalPayment;

import java.util.List;

public interface PartialWithdrawalPaymentRepository extends JpaRepository<PartialWithdrawalPayment, Long> {
    List<PartialWithdrawalPayment> findAllByPolicyNumber(String policyNumber);
}
