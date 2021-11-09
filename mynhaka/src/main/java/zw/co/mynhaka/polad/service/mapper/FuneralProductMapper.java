package zw.co.mynhaka.polad.service.mapper;

import org.mapstruct.*;
import zw.co.mynhaka.polad.domain.dtos.funeral.FuneralProductCreateDto;
import zw.co.mynhaka.polad.domain.dtos.funeral.FuneralProductResultDTO;
import zw.co.mynhaka.polad.domain.dtos.funeral.FuneralProductReverseCreateDto;
import zw.co.mynhaka.polad.domain.dtos.funeral.FuneralProductUpdateDto;
import zw.co.mynhaka.polad.domain.model.Funeral;
import zw.co.mynhaka.polad.domain.model.FuneralProduct;

import java.math.BigDecimal;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FuneralProductMapper {
    @Mapping(target = "funeralId", source = "funeralProduct.funeral.id")
    FuneralProductResultDTO toFuneralProductResultDTO(FuneralProduct funeralProduct);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "funeralProductCreateDto.name")
    FuneralProduct toFuneralProduct(Funeral funeral, FuneralProductCreateDto funeralProductCreateDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "funeralProductReverseCreateDto.name")
    @Mapping(target = "premium", source = "funeralProductReverseCreateDto.sumAssured")
    FuneralProduct toFuneralProduct(Funeral funeral, FuneralProductReverseCreateDto funeralProductReverseCreateDto);

    @Mapping(target = "premium", source = "funeralProductUpdateDto.sumAssured")
    void updateFuneralProduct(@MappingTarget FuneralProduct funeralProduct, FuneralProductUpdateDto funeralProductUpdateDto);

    @Named("funeralCostPerThousand")
    static BigDecimal reversedPremium(BigDecimal sumAssured) {
        //TODO get funeralCostPerThousand from properties file
        return sumAssured.divide(new BigDecimal(1000)).multiply(BigDecimal.valueOf(0.6));
    }
}
