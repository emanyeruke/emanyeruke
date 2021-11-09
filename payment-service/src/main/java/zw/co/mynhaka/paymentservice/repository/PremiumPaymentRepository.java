package zw.co.mynhaka.paymentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.mynhaka.paymentservice.domain.model.PremiumPayment;

public interface PremiumPaymentRepository extends JpaRepository<PremiumPayment, Long> {
}
