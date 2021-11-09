package zw.co.mynhaka.polad.service.iface;


import zw.co.mynhaka.polad.domain.dtos.funeral.FuneralProductCreateDto;
import zw.co.mynhaka.polad.domain.dtos.funeral.FuneralProductResultDTO;
import zw.co.mynhaka.polad.domain.dtos.funeral.FuneralProductReverseCreateDto;
import zw.co.mynhaka.polad.domain.dtos.funeral.FuneralProductUpdateDto;
import zw.co.mynhaka.polad.domain.model.FuneralProduct;

import java.util.List;

public interface FuneralProductService {

    List<FuneralProductResultDTO> findAll();

    FuneralProductResultDTO findById(Long id);

    FuneralProductResultDTO save(FuneralProductCreateDto funeralProductCreateDto);

    FuneralProductResultDTO save(FuneralProductUpdateDto funeralProductUpdateDto);

    FuneralProduct getOne(Long id);

    void deleteById(Long id);

    FuneralProductResultDTO saveReverse(FuneralProductReverseCreateDto funeralProductCreateDto);
}
