package zw.co.mynhaka.polad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.mynhaka.polad.domain.model.Penalty;

import java.util.Optional;

public interface PenaltyRepository extends JpaRepository<Penalty, Long> {
    Optional<Penalty> findByMonth(int month);
}
