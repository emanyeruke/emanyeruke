package zw.co.mynhaka.policyservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.mynhaka.policyservice.domain.model.SavingsPartialWithdrawal;

public interface SavingsWithdrawalRepository extends JpaRepository<SavingsPartialWithdrawal, Long> {
}
