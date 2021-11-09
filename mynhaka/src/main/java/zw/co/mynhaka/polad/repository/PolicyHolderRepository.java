package zw.co.mynhaka.polad.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import zw.co.mynhaka.polad.domain.model.Employer;
import zw.co.mynhaka.polad.domain.model.PolicyHolder;

import java.util.List;
import java.util.stream.Stream;


public interface PolicyHolderRepository extends JpaRepository<PolicyHolder, Long>, JpaSpecificationExecutor<PolicyHolder> {

    Stream<PolicyHolder> findAllBy();

    PolicyHolder findByIdNumber(String nationalId);

    Page<PolicyHolder> findAllByOrderByLastnameAsc(Pageable pageable);

    List<PolicyHolder> findAllByEmployer(Employer employer);
}
