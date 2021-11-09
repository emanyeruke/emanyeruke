package zw.co.mynhaka.polad.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.mynhaka.polad.domain.model.LegalGuardian;


public interface LegalGuardianRepository extends JpaRepository<LegalGuardian, Long> {
    Page<LegalGuardian> findAllBy(Pageable pageable);
}
