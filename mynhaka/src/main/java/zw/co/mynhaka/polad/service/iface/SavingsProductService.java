package zw.co.mynhaka.polad.service.iface;


import zw.co.mynhaka.polad.domain.dtos.savings.SavingsProductCreateDto;
import zw.co.mynhaka.polad.domain.dtos.savings.SavingsProductResultDTO;
import zw.co.mynhaka.polad.domain.dtos.savings.SavingsProductReverseCreateDto;
import zw.co.mynhaka.polad.domain.dtos.savings.SavingsProductUpdateDto;
import zw.co.mynhaka.polad.domain.model.SavingsProduct;

import java.util.List;

public interface SavingsProductService {

    List<SavingsProductResultDTO> findAll();

    SavingsProductResultDTO findById(Long id);

    SavingsProductResultDTO save(SavingsProductCreateDto savingsProductCreateDto);

    SavingsProductResultDTO save(SavingsProductUpdateDto savingsProductUpdateDto);

    SavingsProduct getOne(Long id);

    void deleteById(Long id);

    SavingsProductResultDTO saveReverse(SavingsProductReverseCreateDto savingsProductCreateDto);
}
