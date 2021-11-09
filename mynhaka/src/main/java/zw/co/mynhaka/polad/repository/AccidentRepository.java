package zw.co.mynhaka.polad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.mynhaka.polad.domain.model.Accident;


public interface AccidentRepository extends JpaRepository<Accident, Long> {


}
