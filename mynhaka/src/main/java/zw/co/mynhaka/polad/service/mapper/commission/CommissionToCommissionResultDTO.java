package zw.co.mynhaka.polad.service.mapper.commission;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.agent.CommissionResultDTO;
import zw.co.mynhaka.polad.domain.model.Commission;


@Component
public class CommissionToCommissionResultDTO implements Converter<Commission, CommissionResultDTO> {
    @Override
    public CommissionResultDTO convert(Commission commission) {

        CommissionResultDTO commissionResultDTO = new CommissionResultDTO();
        commissionResultDTO.setCommissionId(commission.getId());
        commissionResultDTO.setTiedAgentCommissionRate(commission.getTiedAgentCommissionRate());
        commissionResultDTO.setTiedUnitLeaderCommissionRate(commission.getTiedUnitLeaderCommissionRate());
        commissionResultDTO.setExecutiveAgentCommissionRate(commission.getExecutiveAgentCommissionRate());
        commissionResultDTO.setExecutiveUnitLeaderCommissionRate(commission.getExecutiveUnitLeaderCommissionRate());
        commissionResultDTO.setUpsellAgent(commission.getUpsellAgentCommissionRate());
        commissionResultDTO.setUpsellManager(commission.getUpsellManagerCommissionRate());
       // commissionResultDTO.setMonth(commission.getMonth());
        commissionResultDTO.setParentAgentCommissionRate(commission.getParentAgentCommissionRate());
        commissionResultDTO.setParentUnitLeaderCommissionRate(commission.getParentUnitLeaderCommissionRate());
        commissionResultDTO.setPolicyType(commission.getPolicyType().toString());
        return commissionResultDTO;
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
