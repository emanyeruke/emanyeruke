package zw.co.mynhaka.policyservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.mynhaka.policyservice.domain.enums.SurrenderStatus;
import zw.co.mynhaka.policyservice.domain.model.SavingsSurrender;

public interface SavingsSurrenderRepository extends JpaRepository<SavingsSurrender, Long> {
    Page<SavingsSurrender> findAllBySurrenderStatus(SurrenderStatus surrenderStatus, Pageable pageable);
}
