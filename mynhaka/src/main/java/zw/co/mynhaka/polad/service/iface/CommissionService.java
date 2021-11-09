package zw.co.mynhaka.polad.service.iface;

import zw.co.mynhaka.polad.domain.dtos.agent.CommissionResultDTO;
import zw.co.mynhaka.polad.domain.model.Commission;

import java.util.List;


public interface CommissionService {

    List<Commission> findAll();

    CommissionResultDTO findById(Long id);

    String add(Commission commission);

    Commission update (Commission commission);

    Commission getCommissionByPolicyType(String policyType);

   // int getFinalCommissionPaymentMonthByPolicyType(String policyType);

    void deleteById(Long id);

    Commission getOne(Long id);

}
