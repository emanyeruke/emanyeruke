package zw.co.mynhaka.polad.service.mapper.allocation;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.allocation.AllocationCreateDTO;
import zw.co.mynhaka.polad.domain.model.Allocation;
import zw.co.mynhaka.polad.service.iface.PaymentService;
import zw.co.mynhaka.polad.service.iface.PolicyAccidentService;


@Component
public class AllocationCreateToAllocation implements Converter<AllocationCreateDTO, Allocation> {

    private final PaymentService paymentService;
    private final PolicyAccidentService policyAccidentService;

    public AllocationCreateToAllocation(final PaymentService paymentService,
                                        final PolicyAccidentService policyAccidentService) {
        this.paymentService = paymentService;
        this.policyAccidentService = policyAccidentService;
    }


    @Override
    public Allocation convert(AllocationCreateDTO allocationCreateDTO) {
        Allocation allocation = new Allocation();
        allocation.setAmount(allocationCreateDTO.getAmount());
        allocation.setPayment(paymentService.getOne(allocationCreateDTO.getPaymentId()));
        allocation.setPolicyAccident(policyAccidentService.findByPolicyNumber(allocationCreateDTO.getPolicyNumber()));
        return allocation;
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
