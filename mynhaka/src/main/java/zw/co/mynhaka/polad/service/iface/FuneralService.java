package zw.co.mynhaka.polad.service.iface;


import zw.co.mynhaka.polad.domain.dtos.ProductCreateDto;
import zw.co.mynhaka.polad.domain.dtos.ProductResultDto;
import zw.co.mynhaka.polad.domain.dtos.ProductUpdateDto;
import zw.co.mynhaka.polad.domain.dtos.funeral.FuneralResultDTO;
import zw.co.mynhaka.polad.domain.model.Funeral;

import java.util.List;

public interface FuneralService {

    List<FuneralResultDTO> findAll();

    FuneralResultDTO findById(Long id);

    ProductResultDto save(ProductCreateDto productCreateDto);

    ProductResultDto save(ProductUpdateDto productUpdateDto);

    void deleteById(Long id);

    Funeral getOne(Long id);
}
