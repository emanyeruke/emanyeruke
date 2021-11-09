package zw.co.mynhaka.polad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.mynhaka.polad.domain.model.PremiumPayment;

import java.util.List;

public interface PremiumPaymentRepository extends JpaRepository<PremiumPayment, Long> {
    List<PremiumPayment> findAllByPolicyNumberOrderByPaymentDate(String policyNumber);
}
