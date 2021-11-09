package zw.co.mynhaka.polad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.mynhaka.polad.domain.model.Policy;
import zw.co.mynhaka.polad.domain.model.PolicyHolder;

import java.util.List;
import java.util.Optional;

public interface PolicyRepository<T extends Policy> extends JpaRepository<T, Long> {
    Optional<Policy> findByPolicyNumber(String policyNumber);

    List<Policy> findAllByPolicyHolder(PolicyHolder policyHolder);

    Optional<Policy> findByPolicyHolder(PolicyHolder policyHolder);
}
