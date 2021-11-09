package zw.co.mynhaka.policyholderservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.mynhaka.policyholderservice.domain.models.Agent;
import zw.co.mynhaka.policyholderservice.domain.models.Manager;

import java.util.List;

public interface AgentRepository extends JpaRepository<Agent, Long> {
    List<Agent> findAllByManager(Manager manager);
    Page<Agent> findAllByManager(Manager manager, Pageable pageable);
}
