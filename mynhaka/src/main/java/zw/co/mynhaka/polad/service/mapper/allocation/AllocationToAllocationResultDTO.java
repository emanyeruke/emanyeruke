package zw.co.mynhaka.polad.service.mapper.allocation;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.allocation.AllocationResultDTO;
import zw.co.mynhaka.polad.domain.model.Allocation;

@Component
public class AllocationToAllocationResultDTO implements Converter<Allocation, AllocationResultDTO> {
    @Override
    public AllocationResultDTO convert(Allocation allocation) {

        AllocationResultDTO allocationResultDTO = new AllocationResultDTO();
        if (allocation.getPolicyAccident() != null) {
            allocationResultDTO.setAccidentPolicyNumber(allocation.getPolicyAccident().getPolicyNumber());
        }
        if (allocation.getPolicyComprehensive() != null) {
            allocationResultDTO.setComprehensivePolicyNumber(allocation.getPolicyComprehensive().getPolicyNumber());
        }
        if (allocation.getPolicyFuneral() != null) {
            allocationResultDTO.setFuneralPolicyNumber(allocation.getPolicyFuneral().getPolicyNumber());
        }
        if (allocation.getPolicySavings() != null) {
            allocationResultDTO.setSavingsPolicyNumber(allocation.getPolicySavings().getPolicyNumber());

        }
        allocationResultDTO.setId(allocation.getId());
        allocationResultDTO.setAmount(allocation.getAmount());
        allocationResultDTO.setPaymentID(allocation.getPayment().getId());

        return allocationResultDTO;
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return null;
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return null;
    }
}
