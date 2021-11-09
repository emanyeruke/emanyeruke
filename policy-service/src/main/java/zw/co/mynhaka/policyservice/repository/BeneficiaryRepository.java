package zw.co.mynhaka.policyservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.mynhaka.policyservice.domain.model.Beneficiary;
import zw.co.mynhaka.policyservice.domain.model.Policy;

import java.util.List;
import java.util.Optional;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {
    Optional<Beneficiary> findByIdNumber(String idNumber);

    List<Beneficiary> findAllByPolicy(Policy policy);
}
