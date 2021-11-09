package zw.co.mynhaka.policyservice.service.mapper;

import org.mapstruct.*;
import zw.co.mynhaka.policyservice.domain.dto.comprehensiveplan.ComprehensiveFuneralPlanCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.comprehensiveplan.ComprehensiveFuneralPlanResultDTO;
import zw.co.mynhaka.policyservice.domain.dto.comprehensiveplan.ComprehensiveFuneralPlanReverseCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.comprehensiveplan.ComprehensiveFuneralPlanUpdateDTO;
import zw.co.mynhaka.policyservice.domain.model.ComprehensiveFuneralPlan;
import zw.co.mynhaka.policyservice.domain.model.Product;
import zw.co.mynhaka.policyservice.service.utils.IgnoreAuditing;

import java.math.BigDecimal;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ComprehensiveFuneralPlanMapper {
    @IgnoreAuditing
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "comprehensiveFuneralPlanCreateDTO.name")
    ComprehensiveFuneralPlan toComprehensiveFuneralPlan(ComprehensiveFuneralPlanCreateDTO comprehensiveFuneralPlanCreateDTO, Product product);

    @IgnoreAuditing
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "comprehensiveFuneralPlanReverseCreateDTO.name")
    @Mapping(target = "premium", source = "comprehensiveFuneralPlanReverseCreateDTO.sumAssured", qualifiedByName = "comprehensiveFuneralCostPerThousand")
    ComprehensiveFuneralPlan toComprehensiveFuneralPlan(ComprehensiveFuneralPlanReverseCreateDTO comprehensiveFuneralPlanReverseCreateDTO, Product product);

    @Mapping(target = "productId", source = "comprehensiveFuneralPlan.id")
    ComprehensiveFuneralPlanResultDTO toComprehensiveFuneralPlanProductResultDTO(ComprehensiveFuneralPlan comprehensiveFuneralPlan);

    @IgnoreAuditing
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "comprehensiveFuneralPlanUpdateDTO.name")
    @Mapping(target = "product", source = "product")
    void updateAccidentPlan(@MappingTarget ComprehensiveFuneralPlan comprehensiveFuneralPlan, ComprehensiveFuneralPlanUpdateDTO comprehensiveFuneralPlanUpdateDTO, Product product);

    @Named("comprehensiveFuneralCostPerThousand")
    default BigDecimal reversedPremium(BigDecimal sumAssured) {
        //TODO get accidentCostPerThousand from properties file
        return sumAssured.divide(new BigDecimal(1000)).multiply(BigDecimal.valueOf(0.6));
    }
}
