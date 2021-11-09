package zw.co.mynhaka.polad.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.mynhaka.polad.domain.model.FinancialAdvisor;


public interface FinancialAdvisorRepository extends JpaRepository<FinancialAdvisor, Long> {
    Page<FinancialAdvisor> findAllBy(Pageable pageable);
}
