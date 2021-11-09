package zw.co.mynhaka.polad.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.mynhaka.polad.domain.model.Policy;
import zw.co.mynhaka.polad.domain.model.PolicyAccident;
import zw.co.mynhaka.polad.domain.model.PolicyHolder;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PolicyAccidentRepository extends JpaRepository<PolicyAccident, Long> {

    @EntityGraph(value = "PolicyAccident.policyAccidentProduct", type = EntityGraph.EntityGraphType.LOAD)
    List<PolicyAccident> findPolicyAccidentByPolicyHolder(PolicyHolder policyholder);

    List<PolicyAccident> findPolicyAccidentByPolicyHolder_Id(Long policyholderId);

    @EntityGraph(value = "PolicyAccident.policyAccidentProduct", type = EntityGraph.EntityGraphType.LOAD)
    List<PolicyAccident> findPolicyAccidentByPolicyHolderAndNextInvoicingDate(PolicyHolder policyholder, LocalDate nextInvoicingDate);

    List<PolicyAccident> findPolicyAccidentByPolicyHolder_IdAndNextInvoicingDate(Long policyholderId, LocalDate nextInvoicingDate);

    Optional<Policy> findByPolicyNumber(String policyNumber);

    List<PolicyAccident> findAllByPolicyHolder_Id(Long id);

    List<PolicyAccident> findAllByAgent_Id(Long id);

}
