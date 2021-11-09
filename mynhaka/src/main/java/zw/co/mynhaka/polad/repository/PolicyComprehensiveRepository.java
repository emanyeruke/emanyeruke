package zw.co.mynhaka.polad.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.mynhaka.polad.domain.model.PolicyComprehensive;
import zw.co.mynhaka.polad.domain.model.PolicyHolder;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PolicyComprehensiveRepository extends JpaRepository<PolicyComprehensive, Long> {

    @EntityGraph(value = "PolicyComprehensive.comprehensiveFuneralProduct", type = EntityGraph.EntityGraphType.LOAD)
    List<PolicyComprehensive> findPolicyComprehensiveByPolicyHolder(PolicyHolder policyholder);

    @EntityGraph(value = "PolicyComprehensive.comprehensiveFuneralProduct", type = EntityGraph.EntityGraphType.LOAD)
    List<PolicyComprehensive> findPolicyComprehensiveByPolicyHolderAndNextInvoicingDate(PolicyHolder policyholder, LocalDate nextInvoicingDate);

    List<PolicyComprehensive> findPolicyComprehensiveByPolicyHolder_Id(Long policyHolderId);

    Optional<PolicyComprehensive> findByPolicyNumber(String policyNumber);

    List<PolicyComprehensive> findAllByPolicyHolder_Id(Long id);

    List<PolicyComprehensive> findAllByAgent_Id(Long id);
}
