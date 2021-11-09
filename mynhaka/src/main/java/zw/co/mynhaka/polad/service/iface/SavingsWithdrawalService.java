package zw.co.mynhaka.polad.service.iface;

import org.springframework.data.domain.Page;
import zw.co.mynhaka.polad.domain.dtos.SavingsPartialWithdrawalCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.SavingsPartialWithdrawalResultDTO;
import zw.co.mynhaka.polad.domain.dtos.SavingsPartialWithdrawalUpdateDTO;
import zw.co.mynhaka.polad.domain.model.SavingsPartialWithdrawal;

import java.util.List;

public interface SavingsWithdrawalService {
    SavingsPartialWithdrawalResultDTO save(SavingsPartialWithdrawalCreateDTO savingsPartialWithdrawalCreateDTO);

    List<SavingsPartialWithdrawalResultDTO> update(List<SavingsPartialWithdrawalUpdateDTO> savingsPartialWithdrawalUpdateDTOList);

    Page<SavingsPartialWithdrawalResultDTO> findAll(int page, int size);

    List<SavingsPartialWithdrawalResultDTO> findAllByPolicyNumber(String policyNumber);

    SavingsPartialWithdrawalResultDTO findById(Long id);

    List<SavingsPartialWithdrawal> getAllByPolicyNumber(String policyNumber);

    SavingsPartialWithdrawal getOne(Long id);

    void deleteById(Long id);
}
