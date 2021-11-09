package zw.co.mynhaka.polad.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.mynhaka.polad.domain.model.BeneficiaryComprehensive;
import zw.co.mynhaka.polad.domain.model.PolicyComprehensive;

import java.util.List;

public interface BeneficiaryComprehensiveRepository extends JpaRepository<BeneficiaryComprehensive, Long> {

    @EntityGraph(value = "BeneficiaryComprehensive.comprehensiveFuneral", type = EntityGraph.EntityGraphType.LOAD)
    List<BeneficiaryComprehensive> findAllByPolicyNumber(String policyNumber);

    @EntityGraph(value = "BeneficiaryComprehensive.comprehensiveFuneral", type = EntityGraph.EntityGraphType.LOAD)
    List<BeneficiaryComprehensive> findAllByPolicyComprehensive(PolicyComprehensive policyComprehensive);

    List<BeneficiaryComprehensive> findAllByComprehensiveFuneral_Id(Long id);
}
