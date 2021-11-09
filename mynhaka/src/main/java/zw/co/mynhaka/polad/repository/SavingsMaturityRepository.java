package zw.co.mynhaka.polad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.mynhaka.polad.domain.enums.MaturityStatus;
import zw.co.mynhaka.polad.domain.model.SavingsMaturity;

import java.util.List;

public interface SavingsMaturityRepository extends JpaRepository<SavingsMaturity,Long> {
    List<SavingsMaturity> findAllByMaturityStatus(String maturityStatus);
}
