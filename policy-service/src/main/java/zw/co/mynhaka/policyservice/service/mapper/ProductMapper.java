package zw.co.mynhaka.policyservice.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import zw.co.mynhaka.policyservice.domain.dto.product.ProductCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.product.ProductResultDTO;
import zw.co.mynhaka.policyservice.domain.dto.product.ProductUpdateDTO;
import zw.co.mynhaka.policyservice.domain.model.Product;
import zw.co.mynhaka.policyservice.service.utils.IgnoreAuditing;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {
    @IgnoreAuditing
    @Mapping(target = "id", ignore = true)
    Product toProduct(ProductCreateDTO productCreateDto);

    ProductResultDTO toProductResultDTO(Product product);

    void updateProduct(@MappingTarget Product product, ProductUpdateDTO productUpdateDto);
}
