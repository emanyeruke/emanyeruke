package zw.co.mynhaka.polad.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.polad.domain.dtos.penalty.PenaltyCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.penalty.PenaltyResultDTO;
import zw.co.mynhaka.polad.domain.dtos.penalty.PenaltyUpdateDTO;
import zw.co.mynhaka.polad.domain.model.Penalty;
import zw.co.mynhaka.polad.repository.PenaltyRepository;
import zw.co.mynhaka.polad.service.iface.PenaltyService;
import zw.co.mynhaka.polad.service.exception.BusinessValidationException;
import zw.co.mynhaka.polad.service.mapper.PenaltyMapper;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PenaltyServiceImpl implements PenaltyService {

    private final PenaltyRepository penaltyRepository;
    private final PenaltyMapper penaltyMapper;

    @Override
    public PenaltyResultDTO save(PenaltyCreateDTO penaltyCreateDTO) {
        Penalty penalty = penaltyMapper.toPenalty(penaltyCreateDTO);
        return penaltyMapper.toPenaltyResultDTO(penaltyRepository.save(penalty));
    }

    @Override
    public List<PenaltyResultDTO> save(List<PenaltyCreateDTO> penaltyCreateDTOs) {
        List<Penalty> penalties = penaltyCreateDTOs.stream()
                .map(penaltyMapper::toPenalty)
                .collect(Collectors.toList());

        return penaltyRepository.saveAll(penalties).stream()
                .map(penaltyMapper::toPenaltyResultDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PenaltyResultDTO> update(List<PenaltyUpdateDTO> penaltyUpdateDTOs) {
        List<Penalty> penalties = penaltyUpdateDTOs.stream().map(penaltyUpdateDTO -> {
            Penalty penalty = findOne(penaltyUpdateDTO.getId());
            penaltyMapper.updatePenalty(penalty, penaltyUpdateDTO);
            return penalty;
        }).collect(Collectors.toList());

        return penaltyRepository.saveAll(penalties).stream()
                .map(penaltyMapper::toPenaltyResultDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PenaltyResultDTO update(Long id, PenaltyUpdateDTO penaltyUpdateDTO) {
        Penalty savedPenalty = findOne(id);
        penaltyMapper.updatePenalty(savedPenalty, penaltyUpdateDTO);
        return penaltyMapper.toPenaltyResultDTO(penaltyRepository.save(savedPenalty));
    }

    @Override
    public PenaltyResultDTO getOne(Long id) {
        return penaltyMapper.toPenaltyResultDTO(findOne(id));
    }

    @Override
    public Penalty findOne(Long id) {
        return penaltyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Penalty with id: " + id + " not found"));
    }

    @Override
    public Page<PenaltyResultDTO> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return penaltyRepository.findAll(pageRequest).map(penaltyMapper::toPenaltyResultDTO);
    }

    @Override
    public Penalty findPenaltyByMonth(int month) {
        return penaltyRepository.findByMonth(month)
                .orElseThrow(() -> new BusinessValidationException("No rate has been configured for month " + month));
    }

    @Override
    public void deleteById(Long id) {
        penaltyRepository.deleteById(id);
    }

    @Override
    public void penaltyFaker() {
        List<Penalty> penalties = new ArrayList<>();
        for (int i = 1; i <= 60; i++) {
            if (i <= 23) {
                penalties.add(Penalty.builder()
                        .month(i)
                        .rate(BigDecimal.valueOf(0.02))
                        .build());
            } else if (i <= 36) {
                penalties.add(Penalty.builder()
                        .month(i)
                        .rate(BigDecimal.valueOf(0.01))
                        .build());
            } else if (i < 60) {
                penalties.add(Penalty.builder()
                        .month(i)
                        .rate(BigDecimal.valueOf(0.005))
                        .build());
            } else {
                penalties.add(Penalty.builder()
                        .month(i)
                        .rate(BigDecimal.valueOf(0))
                        .build());
            }
        }
        penaltyRepository.saveAll(penalties);
    }
}
