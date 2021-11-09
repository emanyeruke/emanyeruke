package zw.co.mynhaka.policyservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.mynhaka.policyservice.domain.model.CancelPolicy;

public interface CancelPolicyRepository extends JpaRepository<CancelPolicy, Long> {
}
