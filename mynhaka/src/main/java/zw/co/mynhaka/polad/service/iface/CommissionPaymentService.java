package zw.co.mynhaka.polad.service.iface;

import org.springframework.data.domain.Page;
import zw.co.mynhaka.polad.domain.dtos.commissionpayment.CommissionPaymentResultDTO;
import zw.co.mynhaka.polad.domain.model.CommissionPayment;
import zw.co.mynhaka.polad.domain.model.Payment;
import zw.co.mynhaka.polad.domain.model.PolicyHolder;

import java.util.List;

public interface CommissionPaymentService {

    String initiatePayment(CommissionPayment commissionPayment);

    String initiateUpsellPayment(Long commissionPaymentId, Long upsellAgentId, Long upsellManagerId);

    String update (CommissionPayment commissionPayment);


    CommissionPayment getOne( Long id);

    List<CommissionPayment> findAll();


    Page<CommissionPaymentResultDTO> getAllCommissionPayments(int page, int size);

    Page<CommissionPaymentResultDTO> getAllCommissionPaymentsByStatus(String paymentStatus, int page, int size);
}
