package zw.co.mynhaka.polad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.mynhaka.polad.domain.model.Manager;


public interface ManagerRepository extends JpaRepository<Manager, Long> {

    /*@EntityGraph(value = "Manager.agents", type = EntityGraph.EntityGraphType.LOAD)
    Page<Manager> findAll(Pageable pageable);*/
}
