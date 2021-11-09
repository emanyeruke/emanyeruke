package zw.co.mynhaka.policyservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.mynhaka.policyservice.domain.model.SavingsPlan;

public interface SavingsPlanRepository extends JpaRepository<SavingsPlan, Long> {
}
