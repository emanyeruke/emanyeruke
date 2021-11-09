package zw.co.mynhaka.polad.service.iface;


import zw.co.mynhaka.polad.domain.dtos.comprehensivefuneral.ComprehensiveFuneralProductResultDTO;
import zw.co.mynhaka.polad.domain.dtos.comprehensivefuneral.ComprehensiveFuneralProductReverseCreateDto;
import zw.co.mynhaka.polad.domain.dtos.comprehensivefuneral.ComprehensiveProductCreateDto;
import zw.co.mynhaka.polad.domain.dtos.comprehensivefuneral.ComprehensiveProductUpdateDto;
import zw.co.mynhaka.polad.domain.model.ComprehensiveFuneralProduct;

import java.util.List;

public interface ComprehensiveProductService {

    List<ComprehensiveFuneralProductResultDTO> findAll();

    ComprehensiveFuneralProductResultDTO findById(Long id);

    ComprehensiveFuneralProductResultDTO save(ComprehensiveProductCreateDto comprehensiveProductCreateDto);

    ComprehensiveFuneralProductResultDTO save(ComprehensiveProductUpdateDto comprehensiveProductUpdateDto);

    ComprehensiveFuneralProductResultDTO saveReverse(ComprehensiveFuneralProductReverseCreateDto comprehensiveProductCreateDto);

    ComprehensiveFuneralProduct getOne(Long id);

    void deleteById(Long id);
}
