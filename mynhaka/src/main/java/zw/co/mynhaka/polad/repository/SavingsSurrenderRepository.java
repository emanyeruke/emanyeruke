package zw.co.mynhaka.polad.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.mynhaka.polad.domain.enums.SurrenderStatus;
import zw.co.mynhaka.polad.domain.model.SavingsSurrender;

public interface SavingsSurrenderRepository extends JpaRepository<SavingsSurrender, Long> {
    Page<SavingsSurrender> findAllBySurrenderStatus(SurrenderStatus status, PageRequest page);
}
