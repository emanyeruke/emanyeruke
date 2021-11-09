package zw.co.mynhaka.policyservice.service;

import org.springframework.data.domain.Page;
import zw.co.mynhaka.policyservice.domain.dto.payment.WithdrawalPaymentCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.savingspartialwithdrawal.SavingsPartialWithdrawalCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.savingspartialwithdrawal.SavingsPartialWithdrawalResultDTO;
import zw.co.mynhaka.policyservice.domain.model.SavingsPartialWithdrawal;

import java.util.List;

public interface SavingsWithdrawalService {
    SavingsPartialWithdrawalResultDTO save(String policyNumber, SavingsPartialWithdrawalCreateDTO savingsPartialWithdrawalCreateDTO);

    SavingsPartialWithdrawalResultDTO approveWithdrawal(Long withdrawalId);

    SavingsPartialWithdrawalResultDTO approveWithdrawalPayment(WithdrawalPaymentCreateDTO withdrawalPaymentCreateDTO);

    List<SavingsPartialWithdrawalResultDTO> approveWithdrawalPayment(List<WithdrawalPaymentCreateDTO> withdrawalPaymentCreateDTOList);

    Page<SavingsPartialWithdrawalResultDTO> findAll(int page, int size);

    List<SavingsPartialWithdrawalResultDTO> findAll();

    SavingsPartialWithdrawal getOne(Long id);

    void deleteById(Long withdrawalId);
}
