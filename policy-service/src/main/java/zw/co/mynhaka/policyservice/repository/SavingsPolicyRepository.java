package zw.co.mynhaka.policyservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.mynhaka.policyservice.domain.model.SavingsPolicy;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SavingsPolicyRepository extends JpaRepository<SavingsPolicy, Long> {
    List<SavingsPolicy> findAllByPolicyHolderId(Long policyHolderId);

    Optional<SavingsPolicy> findByPolicyHolderIdAndNextInvoicingDate(Long policyHolderId, LocalDate nextInvoicingDate);

    Optional<SavingsPolicy> findByPolicyNumber(String policyNumber);
}
