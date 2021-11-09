package zw.co.mynhaka.polad.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import zw.co.mynhaka.polad.domain.model.CommissionPayment;
import zw.co.mynhaka.polad.service.iface.reports.dtos.CommissionsPerProductResult;

import java.util.List;

public interface CommissionPaymentRepository extends JpaRepository<CommissionPayment, Long> {
    Page<CommissionPayment> findAllByPaymentStatus(String paymentStatus, Pageable pageable);

    @Query(value = "SELECT year(created_date) as year, month(created_date) as month, policy_type as policy, sum(amount) as total FROM commission_payment " +
            "WHERE year(created_date) = year(current_date()) AND payment_status = 'VALIDATED'" +
            " GROUP BY year, month, policy", nativeQuery = true)
    List<CommissionsPerProductResult> getCommissionsPaidPerProduct();
}
