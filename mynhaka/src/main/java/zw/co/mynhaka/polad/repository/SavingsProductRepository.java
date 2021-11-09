package zw.co.mynhaka.polad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.mynhaka.polad.domain.model.SavingsProduct;


public interface SavingsProductRepository extends JpaRepository<SavingsProduct, Long> {
}
