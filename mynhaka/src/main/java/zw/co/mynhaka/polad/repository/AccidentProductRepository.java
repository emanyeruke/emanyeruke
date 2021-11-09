package zw.co.mynhaka.polad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.mynhaka.polad.domain.model.AccidentProduct;


public interface AccidentProductRepository extends JpaRepository<AccidentProduct, Long> {
    //List<AccidentPolicyResponse> findAllBy();
}
