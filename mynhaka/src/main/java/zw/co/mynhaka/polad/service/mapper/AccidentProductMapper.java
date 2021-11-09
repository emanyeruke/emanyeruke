package zw.co.mynhaka.polad.service.mapper;

import org.mapstruct.*;
import zw.co.mynhaka.polad.domain.dtos.accident.AccidentProductCreateDto;
import zw.co.mynhaka.polad.domain.dtos.accident.AccidentProductResultDTO;
import zw.co.mynhaka.polad.domain.dtos.accident.AccidentProductReverse;
import zw.co.mynhaka.polad.domain.dtos.accident.AccidentProductUpdateDto;
import zw.co.mynhaka.polad.domain.model.Accident;
import zw.co.mynhaka.polad.domain.model.AccidentProduct;

import java.math.BigDecimal;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccidentProductMapper {
    @Mapping(target = "accidentId", source = "accidentProduct.accident.id")
    AccidentProductResultDTO toAccidentProductResultDTO(AccidentProduct accidentProduct);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "accidentProductCreateDto.name")
    AccidentProduct toAccidentProduct(Accident accident, AccidentProductCreateDto accidentProductCreateDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "accidentProductCreateDto.name")
    @Mapping(target = "premium", source = "accidentProductCreateDto.sumAssured")
    AccidentProduct toAccidentProduct(Accident accident, AccidentProductReverse accidentProductCreateDto);

    @Mapping(target = "premium", source = "accidentProductUpdateDto.sumAssured")
    void updateAccidentProduct(@MappingTarget AccidentProduct accidentProduct, AccidentProductUpdateDto accidentProductUpdateDto);

    @Named("accidentCostPerThousand")
    default BigDecimal reversedPremium(BigDecimal sumAssured) {
        //TODO get accidentCostPerThousand from properties file
        return sumAssured.divide(new BigDecimal(1000)).multiply(BigDecimal.valueOf(0.6));
    }
}
