package zw.co.mynhaka.policyholderservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.mynhaka.policyholderservice.domain.models.Employer;
import zw.co.mynhaka.policyholderservice.domain.models.Representative;

public interface RepresentativeRepository extends JpaRepository<Representative, Long> {
    Page<Representative> findAllByEmployer(Employer employer, Pageable pageable);
}
