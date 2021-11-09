package zw.co.mynhaka.policyservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.mynhaka.policyservice.domain.model.StandardFuneralPlan;

public interface StandardFuneralPlanRepository extends JpaRepository<StandardFuneralPlan, Long> {
}
