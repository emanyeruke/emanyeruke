package zw.co.mynhaka.polad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.mynhaka.polad.domain.model.Funeral;


public interface FuneralRepository extends JpaRepository<Funeral, Long> {
}
