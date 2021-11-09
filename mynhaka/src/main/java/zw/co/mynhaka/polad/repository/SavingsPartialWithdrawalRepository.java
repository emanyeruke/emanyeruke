package zw.co.mynhaka.polad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.mynhaka.polad.domain.model.SavingsPartialWithdrawal;

import java.util.List;

public interface SavingsPartialWithdrawalRepository extends JpaRepository<SavingsPartialWithdrawal, Long> {
    List<SavingsPartialWithdrawal> findAllByPolicyNumberOrderByCreatedDate(String policyNumber);

//    Page<SavingsPartialWithdrawal> findAllByPolicyNumber(String policyNumber, Pageable pageable);
}
