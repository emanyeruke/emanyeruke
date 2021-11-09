package zw.co.mynhaka.polad.service.iface;


import zw.co.mynhaka.polad.domain.dtos.ProductCreateDto;
import zw.co.mynhaka.polad.domain.dtos.ProductResultDto;
import zw.co.mynhaka.polad.domain.dtos.ProductUpdateDto;
import zw.co.mynhaka.polad.domain.model.Savings;

import java.util.List;

public interface SavingsService {
    List<ProductResultDto> findAll();

    ProductResultDto findById(Long id);

    ProductResultDto save(ProductCreateDto productCreateDto);

    ProductResultDto save(ProductUpdateDto productUpdateDto);

    void deleteById(Long id);

    Savings getOne(Long id);
}
