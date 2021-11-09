package zw.co.mynhaka.policyholderservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.mynhaka.policyholderservice.domain.models.Employer;
import zw.co.mynhaka.policyholderservice.domain.models.PolicyHolder;

import java.util.List;
import java.util.Optional;

public interface PolicyHolderRepository extends JpaRepository<PolicyHolder, Long> {
    Page<PolicyHolder> findAllByOrderByLastnameAsc(Pageable pageable);
    Optional<PolicyHolder> findByIdNumber(String idNumber);
    Page<PolicyHolder> findAllByEmployerOrderByLastnameAsc(Employer employer, Pageable pageable);
    List<PolicyHolder> findAllByEmployer(Employer employer);
}
