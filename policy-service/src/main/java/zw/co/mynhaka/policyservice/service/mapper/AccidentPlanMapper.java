package zw.co.mynhaka.policyservice.service.mapper;

import org.mapstruct.*;
import zw.co.mynhaka.policyservice.domain.dto.accidentplan.AccidentPlanCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.accidentplan.AccidentPlanCreateReverse;
import zw.co.mynhaka.policyservice.domain.dto.accidentplan.AccidentPlanResultDTO;
import zw.co.mynhaka.policyservice.domain.dto.accidentplan.AccidentPlanUpdateDto;
import zw.co.mynhaka.policyservice.domain.model.AccidentPlan;
import zw.co.mynhaka.policyservice.domain.model.Product;
import zw.co.mynhaka.policyservice.service.utils.IgnoreAuditing;

import java.math.BigDecimal;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccidentPlanMapper {
    @IgnoreAuditing
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "accidentPlanCreateDTO.name")
    AccidentPlan toAccidentPlan(AccidentPlanCreateDTO accidentPlanCreateDTO, Product product);

    @IgnoreAuditing
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "accidentPlanCreateReverse.name")
    @Mapping(target = "premium", source = "accidentPlanCreateReverse.sumAssured", qualifiedByName = "accidentCostPerThousand")
    AccidentPlan toAccidentPlan(AccidentPlanCreateReverse accidentPlanCreateReverse, Product product);

    @Mapping(target = "productId", source = "accidentPlan.id")
    AccidentPlanResultDTO toAccidentPlanResultDTO(AccidentPlan accidentPlan);

    @IgnoreAuditing
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "accidentPlanUpdateDto.name")
    @Mapping(target = "product", source = "product")
    void updateAccidentPlan(@MappingTarget AccidentPlan accidentPlan, AccidentPlanUpdateDto accidentPlanUpdateDto, Product product);

    @Named("accidentCostPerThousand")
    default BigDecimal reversedPremium(BigDecimal sumAssured) {
        //TODO get accidentCostPerThousand from properties file
        return sumAssured.divide(new BigDecimal(1000)).multiply(BigDecimal.valueOf(0.6));
    }
}
