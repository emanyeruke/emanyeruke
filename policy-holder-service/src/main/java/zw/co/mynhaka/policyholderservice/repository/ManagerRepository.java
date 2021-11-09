package zw.co.mynhaka.policyholderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.mynhaka.policyholderservice.domain.models.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
}
