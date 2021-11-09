package zw.co.mynhaka.polad.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.mynhaka.polad.domain.model.PolicyHolder;
import zw.co.mynhaka.polad.domain.model.PolicySavings;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PolicySavingsRepository extends JpaRepository<PolicySavings,Long> {

    @EntityGraph(value = "PolicySavings.savingsProduct", type = EntityGraph.EntityGraphType.LOAD)
    List<PolicySavings> findPolicySavingsByPolicyHolder(PolicyHolder policyholder);


    @EntityGraph(value = "PolicySavings.savingsProduct", type = EntityGraph.EntityGraphType.LOAD)
    List<PolicySavings> findPolicySavingsByPolicyHolderAndNextInvoicingDate(PolicyHolder policyholder, LocalDate nextInvoicingDate);

    List<PolicySavings> findPolicySavingsByPolicyHolder_Id(Long policyHolderId);

    Optional<PolicySavings> findByPolicyNumber(String policyNumber);

    List<PolicySavings> findAllByPolicyHolder_Id(Long id);

    List<PolicySavings> findAllByAgent_Id(Long id);
}
