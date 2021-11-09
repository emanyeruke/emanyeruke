package zw.co.mynhaka.polad.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import zw.co.mynhaka.polad.domain.model.Commission;

import java.util.Optional;


public interface CommissionRepository extends JpaRepository<Commission, Long> {

    Page<Commission> findAll(Pageable pageable);

    Optional<Commission> findByPolicyType(String policyType);

   /* @Query(value = "SELECT month FROM Commission WHERE policyType = :policyType ORDER BY month DESC LIMIT 1", nativeQuery = true)
    int getFinalCommissionPaymentMonthByPolicyType(String policyType);

    */
}
