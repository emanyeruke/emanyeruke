package zw.co.mynhaka.polad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.mynhaka.polad.domain.model.Representative;


public interface RepresentativeRepository extends JpaRepository<Representative, Long> {
}
