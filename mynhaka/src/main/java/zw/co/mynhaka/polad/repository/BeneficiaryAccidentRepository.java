package zw.co.mynhaka.polad.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.mynhaka.polad.domain.model.BeneficiaryAccident;
import zw.co.mynhaka.polad.domain.model.PolicyAccident;

import java.util.List;
import java.util.Optional;

public interface BeneficiaryAccidentRepository extends JpaRepository<BeneficiaryAccident, Long> {

    @EntityGraph(value = "BeneficiaryAccident.accidentProduct", type = EntityGraph.EntityGraphType.LOAD)
    List<BeneficiaryAccident> findAllByPolicyNumber(String policyNumber);

    @EntityGraph(value = "BeneficiaryAccident.accidentProduct", type = EntityGraph.EntityGraphType.LOAD)
    List<BeneficiaryAccident> findAllByPolicyAccident(PolicyAccident policyAccident);

    List<BeneficiaryAccident> findAllByPolicyAccident_Id(Long id);

    Optional<BeneficiaryAccident> findByPolicyAccident_IdAndId(Long policyId, Long id);


}
