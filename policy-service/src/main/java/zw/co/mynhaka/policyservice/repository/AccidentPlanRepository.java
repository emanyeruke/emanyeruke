package zw.co.mynhaka.policyservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.mynhaka.policyservice.domain.model.AccidentPlan;

public interface AccidentPlanRepository extends JpaRepository<AccidentPlan, Long> {
}
