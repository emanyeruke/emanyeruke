package zw.co.mynhaka.polad.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import zw.co.mynhaka.polad.domain.dtos.ProductCreateDto;
import zw.co.mynhaka.polad.domain.dtos.ProductResultDto;
import zw.co.mynhaka.polad.domain.dtos.ProductUpdateDto;
import zw.co.mynhaka.polad.domain.model.*;
import zw.co.mynhaka.polad.service.util.IgnoreAuditing;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {
    ProductResultDto toProductResultDto(Product product);

    @IgnoreAuditing
    @Mapping(target = "id", ignore = true)
    Funeral toFuneral(ProductCreateDto productCreateDto);

    @Mapping(target = "id", ignore = true)
    void updateFuneral(@MappingTarget Funeral funeral, ProductUpdateDto productUpdateDto);

    ProductResultDto funeralToProductResultDto(Funeral funeral);

    @IgnoreAuditing
    @Mapping(target = "id", ignore = true)
    Accident toAccident(ProductCreateDto productCreateDto);

    @Mapping(target = "id", ignore = true)
    void updateAccident(@MappingTarget Accident accident, ProductUpdateDto productUpdateDto);

    ProductResultDto accidentToProductResultDto(Accident accident);

    @IgnoreAuditing
    @Mapping(target = "id", ignore = true)
    ComprehensiveFuneral toComprehensiveFuneral(ProductCreateDto productCreateDto);

    @Mapping(target = "id", ignore = true)
    void updateComprehensiveFuneral(@MappingTarget ComprehensiveFuneral comprehensiveFuneral, ProductUpdateDto productUpdateDto);

    ProductResultDto comprehensiveFuneralToProductResultDto(ComprehensiveFuneral comprehensiveFuneral);

    @IgnoreAuditing
    @Mapping(target = "id", ignore = true)
    Savings toSavings(ProductCreateDto productCreateDto);

    @Mapping(target = "id", ignore = true)
    void updateSavings(@MappingTarget Savings savings, ProductUpdateDto productUpdateDto);

    ProductResultDto savingsToProductResultDto(Savings savings);
}
