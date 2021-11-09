package zw.co.mynhaka.policyservice.service.mapper;

import org.mapstruct.*;
import zw.co.mynhaka.policyservice.domain.dto.funeralplan.StandardFuneralPlanCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.funeralplan.StandardFuneralPlanResultDTO;
import zw.co.mynhaka.policyservice.domain.dto.funeralplan.StandardFuneralPlanReverseCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.funeralplan.StandardFuneralPlanUpdateDTO;
import zw.co.mynhaka.policyservice.domain.model.Product;
import zw.co.mynhaka.policyservice.domain.model.StandardFuneralPlan;
import zw.co.mynhaka.policyservice.service.utils.IgnoreAuditing;

import java.math.BigDecimal;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StandardFuneralPlanMapper {
    @IgnoreAuditing
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "standardFuneralPlanCreateDTO.name")
    StandardFuneralPlan toStandardFuneralPlan(StandardFuneralPlanCreateDTO standardFuneralPlanCreateDTO, Product product);

    @IgnoreAuditing
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "standardFuneralPlanReverseCreateDTO.name")
    @Mapping(target = "premium", source = "standardFuneralPlanReverseCreateDTO.sumAssured", qualifiedByName = "standardFuneralCostPerThousand")
    StandardFuneralPlan toStandardFuneralPlan(StandardFuneralPlanReverseCreateDTO standardFuneralPlanReverseCreateDTO, Product product);

    @Mapping(target = "productId", source = "standardFuneralPlan.id")
    StandardFuneralPlanResultDTO toStandardFuneralPlanResultDTO(StandardFuneralPlan standardFuneralPlan);

    @IgnoreAuditing
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", source = "product")
    @Mapping(target = "name", source = "standardFuneralPlanUpdateDTO.name")
    void updateStandardFuneralPlan(@MappingTarget StandardFuneralPlan standardFuneralPlan, StandardFuneralPlanUpdateDTO standardFuneralPlanUpdateDTO, Product product);

    @Named("standardFuneralCostPerThousand")
    default BigDecimal reversedPremium(BigDecimal sumAssured) {
        //TODO get accidentCostPerThousand from properties file
        return sumAssured.divide(new BigDecimal(1000)).multiply(BigDecimal.valueOf(0.6));
    }
}
