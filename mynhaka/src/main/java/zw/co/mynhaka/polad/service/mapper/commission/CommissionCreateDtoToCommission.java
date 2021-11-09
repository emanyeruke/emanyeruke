package zw.co.mynhaka.polad.service.mapper.commission;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.agent.CommissionCreateDTO;
import zw.co.mynhaka.polad.domain.model.Commission;

@Component
public class CommissionCreateDtoToCommission implements Converter<CommissionCreateDTO, Commission> {
    @Override
    public Commission convert(CommissionCreateDTO commissionCreateDTO) {
        Commission commission = new Commission();
        //commission.setMonth(commissionCreateDTO.getMonth());
        commission.setTiedAgentCommissionRate(commissionCreateDTO.getTiedAgentCommissionRate());
        commission.setTiedUnitLeaderCommissionRate(commissionCreateDTO.getTiedUnitLeaderCommissionRate());
        commission.setExecutiveAgentCommissionRate(commissionCreateDTO.getExecutiveAgentCommissionRate());
        commission.setExecutiveUnitLeaderCommissionRate(commissionCreateDTO.getExecutiveUnitLeaderCommissionRate());
        commission.setPolicyType(commissionCreateDTO.getPolicyType());
        commission.setParentAgentCommissionRate(commissionCreateDTO.getParentAgentCommissionRate());
        commission.setParentUnitLeaderCommissionRate(commissionCreateDTO.getParentUnitLeaderCommissionRate());
        commission.setUpsellAgentCommissionRate(commissionCreateDTO.getUpsellAgent());
        commission.setUpsellManagerCommissionRate(commissionCreateDTO.getUpsellManager());
        return commission;
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
