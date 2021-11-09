package zw.co.mynhaka.polad.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.mynhaka.polad.domain.model.BeneficiarySavings;
import zw.co.mynhaka.polad.domain.model.PolicySavings;

import java.util.List;

public interface BeneficiarySavingsRepository extends JpaRepository<BeneficiarySavings, Long> {

    @EntityGraph(value = "BeneficiarySavings.savings", type = EntityGraph.EntityGraphType.LOAD)
    List<BeneficiarySavings> findAllByPolicyNumber(String policyNumber);

    @EntityGraph(value = "BeneficiarySavings.savings", type = EntityGraph.EntityGraphType.LOAD)
    List<BeneficiarySavings> findAllByPolicySavings(PolicySavings policySavings);

    List<BeneficiarySavings> findAllByPolicySavings_Id(Long id);
}
