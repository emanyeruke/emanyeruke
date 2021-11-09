package zw.co.mynhaka.policyholderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.mynhaka.policyholderservice.domain.models.Employer;

public interface EmployerRepository extends JpaRepository<Employer, Long> {
}
