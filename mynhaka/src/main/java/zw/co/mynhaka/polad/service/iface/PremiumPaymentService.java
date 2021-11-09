package zw.co.mynhaka.polad.service.iface;

import zw.co.mynhaka.polad.domain.dtos.PremiumPaymentCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.PremiumPaymentResultDTO;
import zw.co.mynhaka.polad.domain.dtos.PremiumPaymentUpdateDTO;
import zw.co.mynhaka.polad.domain.model.PremiumPayment;

import java.util.List;

public interface PremiumPaymentService {
//    PremiumPaymentResultDTO save(PremiumPaymentCreateDTO paymentCreateDTO);

    List<PremiumPaymentResultDTO> save(List<PremiumPaymentCreateDTO> paymentCreateDTOList);

//    PremiumPaymentResultDTO update(PremiumPaymentUpdateDTO paymentUpdateDTO);

    List<PremiumPaymentResultDTO> update(List<PremiumPaymentUpdateDTO> paymentUpdateDTOList);

    List<PremiumPaymentResultDTO> getAllByPolicyNumber(String policyNumber);

    List<PremiumPayment> getAllValidatedPaymentsByPolicyNumber(String policyNumber);

    PremiumPayment getOne(Long id);
}
