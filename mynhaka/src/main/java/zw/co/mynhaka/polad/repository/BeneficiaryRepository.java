package zw.co.mynhaka.polad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.mynhaka.polad.domain.model.Beneficiary;
import zw.co.mynhaka.polad.domain.model.Policy;

import java.util.List;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {
    List<Beneficiary> findAllByPolicy(Policy policy);
    List<Beneficiary> findByPolicyNumber(String policyNumber);
}
