package zw.co.mynhaka.polad.service.mapper;

import org.mapstruct.*;
import zw.co.mynhaka.polad.domain.dtos.comprehensivefuneral.ComprehensiveFuneralProductResultDTO;
import zw.co.mynhaka.polad.domain.dtos.comprehensivefuneral.ComprehensiveFuneralProductReverseCreateDto;
import zw.co.mynhaka.polad.domain.dtos.comprehensivefuneral.ComprehensiveProductCreateDto;
import zw.co.mynhaka.polad.domain.dtos.comprehensivefuneral.ComprehensiveProductUpdateDto;
import zw.co.mynhaka.polad.domain.model.ComprehensiveFuneral;
import zw.co.mynhaka.polad.domain.model.ComprehensiveFuneralProduct;

import java.math.BigDecimal;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ComprehensiveProductMapper {

    @Mapping(target = "comprehensiveFuneralId", source = "comprehensiveFuneralProduct.comprehensiveFuneral.id")
    ComprehensiveFuneralProductResultDTO toComprehensiveFuneralProductResultDTO(ComprehensiveFuneralProduct comprehensiveFuneralProduct);

    @Mapping(target = "premium", source = "comprehensiveProductUpdateDto.sumAssured")
    void updateComprehensiveFuneralProduct(@MappingTarget ComprehensiveFuneralProduct comprehensiveFuneralProduct, ComprehensiveProductUpdateDto comprehensiveProductUpdateDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "comprehensiveFuneral", source = "comprehensiveFuneral")
    @Mapping(target = "name", source = "comprehensiveFuneralProductReverseCreateDto.name")
    @Mapping(target = "premium", source = "comprehensiveFuneralProductReverseCreateDto.sumAssured")
    ComprehensiveFuneralProduct toComprehensiveFuneralProduct(ComprehensiveFuneral comprehensiveFuneral, ComprehensiveFuneralProductReverseCreateDto comprehensiveFuneralProductReverseCreateDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "comprehensiveFuneral", source = "comprehensiveFuneral")
    @Mapping(target = "name", source = "comprehensiveProductCreateDto.name")
    ComprehensiveFuneralProduct toComprehensiveFuneralProduct(ComprehensiveFuneral comprehensiveFuneral, ComprehensiveProductCreateDto comprehensiveProductCreateDto);

    @Named("comprehensiveCostPerThousand")
    static BigDecimal reversedPremium(BigDecimal sumAssured) {
        //TODO get comprehensiveCostPerThousand from properties file
        return sumAssured.divide(new BigDecimal(1000)).multiply(BigDecimal.valueOf(0.6));
    }
}
