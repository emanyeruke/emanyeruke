package zw.co.mynhaka.polad.service.iface;

import zw.co.mynhaka.polad.domain.dtos.ProductCreateDto;
import zw.co.mynhaka.polad.domain.dtos.ProductResultDto;
import zw.co.mynhaka.polad.domain.dtos.ProductUpdateDto;
import zw.co.mynhaka.polad.domain.model.Product;

import java.util.List;

public interface ProductService {
    List<ProductResultDto> findAll();

    Product findById(long id);

    ProductResultDto save(ProductCreateDto productCreateDto);

    ProductResultDto save(ProductUpdateDto productUpdateDto);

    void delete(long id);
}
