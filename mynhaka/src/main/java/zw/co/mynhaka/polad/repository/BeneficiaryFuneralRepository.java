package zw.co.mynhaka.polad.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.mynhaka.polad.domain.model.BeneficiaryFuneral;
import zw.co.mynhaka.polad.domain.model.PolicyFuneral;

import java.util.List;

public interface BeneficiaryFuneralRepository extends JpaRepository<BeneficiaryFuneral, Long> {

    @EntityGraph(value = "BeneficiaryFuneral.funeral", type = EntityGraph.EntityGraphType.LOAD)
    List<BeneficiaryFuneral> findAllByPolicyNumber(String policyNumber);

    @EntityGraph(value = "BeneficiaryFuneral.funeral", type = EntityGraph.EntityGraphType.LOAD)
    List<BeneficiaryFuneral> findAllByPolicyFuneral(PolicyFuneral policyFuneral);

    List<BeneficiaryFuneral> findAllByPolicyFuneral_Id(Long id);
}
