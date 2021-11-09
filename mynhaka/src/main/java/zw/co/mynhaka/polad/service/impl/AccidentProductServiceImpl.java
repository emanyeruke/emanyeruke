package zw.co.mynhaka.polad.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.polad.domain.dtos.accident.AccidentProductCreateDto;
import zw.co.mynhaka.polad.domain.dtos.accident.AccidentProductResultDTO;
import zw.co.mynhaka.polad.domain.dtos.accident.AccidentProductReverse;
import zw.co.mynhaka.polad.domain.dtos.accident.AccidentProductUpdateDto;
import zw.co.mynhaka.polad.domain.model.Accident;
import zw.co.mynhaka.polad.domain.model.AccidentProduct;
import zw.co.mynhaka.polad.repository.AccidentProductRepository;
import zw.co.mynhaka.polad.service.iface.AccidentProductService;
import zw.co.mynhaka.polad.service.iface.AccidentService;
import zw.co.mynhaka.polad.service.exception.EntityNotFoundException;
import zw.co.mynhaka.polad.service.mapper.AccidentProductMapper;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class AccidentProductServiceImpl implements AccidentProductService {

    private final AccidentProductRepository accidentProductRepository;
    private final AccidentService accidentService;
    private final AccidentProductMapper accidentProductMapper;

    @Override
    public List<AccidentProductResultDTO> findAll() {
        return accidentProductRepository.findAll()
                .stream()
                .map(accidentProductMapper::toAccidentProductResultDTO)
                .sorted(Comparator.comparing(AccidentProductResultDTO::getSumAssured))
                .collect(Collectors.toList());
    }

    @Override
    public AccidentProductResultDTO findById(Long id) {
        return accidentProductMapper.toAccidentProductResultDTO(
                accidentProductRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Accident Product Not found")));
    }

    @Override
    public AccidentProductResultDTO save(AccidentProductCreateDto accidentProductCreateDto) {

        Accident accident = accidentService.getOne(accidentProductCreateDto.getAccidentId());

        AccidentProduct accidentProduct = accidentProductMapper.toAccidentProduct(accident, accidentProductCreateDto);

        return accidentProductMapper.toAccidentProductResultDTO(accidentProductRepository.save(accidentProduct));
    }

    @Override
    public AccidentProductResultDTO save(AccidentProductUpdateDto accidentProductUpdateDto) {
        AccidentProduct accidentProduct = getOne(accidentProductUpdateDto.getAccidentProductId());

        accidentProductMapper.updateAccidentProduct(accidentProduct, accidentProductUpdateDto);

        return accidentProductMapper.toAccidentProductResultDTO(accidentProductRepository.save(accidentProduct));
    }

    @Override
    public void deleteById(Long id) {
        accidentProductRepository.deleteById(id);
    }

    @Override
    public AccidentProductResultDTO saveReverse(AccidentProductReverse accidentProductCreateDto) {
        Accident accident = accidentService.getOne(accidentProductCreateDto.getAccidentId());

        AccidentProduct accidentProduct = accidentProductMapper.toAccidentProduct(accident, accidentProductCreateDto);

        return accidentProductMapper.toAccidentProductResultDTO(accidentProductRepository.save(accidentProduct));
    }

    @Override
    public AccidentProduct getOne(Long id) {
        return accidentProductRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Accident Policy Product not found"));
    }
}
