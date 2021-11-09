package zw.co.mynhaka.polad.service.iface;

import zw.co.mynhaka.polad.domain.dtos.accident.AccidentProductCreateDto;
import zw.co.mynhaka.polad.domain.dtos.accident.AccidentProductResultDTO;
import zw.co.mynhaka.polad.domain.dtos.accident.AccidentProductReverse;
import zw.co.mynhaka.polad.domain.dtos.accident.AccidentProductUpdateDto;
import zw.co.mynhaka.polad.domain.model.AccidentProduct;

import java.util.List;

public interface AccidentProductService {

    List<AccidentProductResultDTO> findAll();

    AccidentProductResultDTO findById(Long id);

    AccidentProductResultDTO save(AccidentProductCreateDto accidentProductCreateDto);

    AccidentProductResultDTO save(AccidentProductUpdateDto accidentProductUpdateDto);

    AccidentProduct getOne(Long id);

    void deleteById(Long id);

    AccidentProductResultDTO saveReverse(AccidentProductReverse accidentProductCreateDto);
}
