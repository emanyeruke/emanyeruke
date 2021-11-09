package zw.co.mynhaka.polad.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.mynhaka.polad.domain.model.PolicyFuneral;
import zw.co.mynhaka.polad.domain.model.PolicyHolder;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PolicyFuneralRepository extends JpaRepository<PolicyFuneral, Long> {

    @EntityGraph(value = "PolicyFuneral.funeralProduct", type = EntityGraph.EntityGraphType.LOAD)
    List<PolicyFuneral> findPolicyFuneralByPolicyHolder(PolicyHolder policyholder);

    @EntityGraph(value = "PolicyFuneral.funeralProduct", type = EntityGraph.EntityGraphType.LOAD)
    List<PolicyFuneral> findPolicyFuneralByPolicyHolderAndNextInvoicingDate(PolicyHolder policyholder, LocalDate nextInvoicingDate);

   List<PolicyFuneral> findPolicyFuneralByPolicyHolder_Id(Long policyHolderId);
    Optional<PolicyFuneral> findByPolicyNumber(String policyNumber);

    List<PolicyFuneral> findAllByPolicyHolder_Id(Long id);

    List<PolicyFuneral> findAllByAgent_Id(Long id);
}
