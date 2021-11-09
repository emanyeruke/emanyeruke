package zw.co.mynhaka.polad.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.mynhaka.polad.domain.model.Savings;


public interface SavingsRepository extends JpaRepository<Savings, Long> {
}
