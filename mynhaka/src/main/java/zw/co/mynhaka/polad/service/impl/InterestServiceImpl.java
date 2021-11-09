package zw.co.mynhaka.polad.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.polad.domain.dtos.InterestCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.InterestResultDTO;
import zw.co.mynhaka.polad.domain.dtos.InterestUpdateDTO;
import zw.co.mynhaka.polad.domain.model.Interest;
import zw.co.mynhaka.polad.repository.InterestRepository;
import zw.co.mynhaka.polad.service.iface.InterestService;
import zw.co.mynhaka.polad.service.exception.EntityNotFoundException;
import zw.co.mynhaka.polad.service.mapper.InterestMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InterestServiceImpl implements InterestService {

    private final InterestRepository interestRepository;
    private final InterestMapper interestMapper;


    @Override
    public List<InterestResultDTO> save(List<InterestCreateDTO> interestCreateDTOs) {
        List<Interest> interests = interestCreateDTOs.stream()
                .map(interestMapper::toInterest)
                .collect(Collectors.toList());

        return interestRepository.saveAll(interests).stream()
                .map(interestMapper::toInterestResultDTO)
                .collect(Collectors.toList());
    }

    @Override
    public InterestResultDTO save(InterestCreateDTO interestCreateDTO) {
        Interest interest = interestMapper.toInterest(interestCreateDTO);
        return interestMapper.toInterestResultDTO(interestRepository.save(interest));
    }

    @Override
    public InterestResultDTO update(Long id, InterestUpdateDTO interestUpdateDTO) {
        Interest interest = findOne(id);
        interestMapper.updateInterest(interest, interestUpdateDTO);
        return interestMapper.toInterestResultDTO(interest);
    }

    @Override
    public List<InterestResultDTO> update(List<InterestUpdateDTO> interestUpdateDTOs) {
        List<Interest> interests = interestUpdateDTOs.stream().map(interestUpdateDTO -> {
            Interest interest = findOne(interestUpdateDTO.getId());
            interestMapper.updateInterest(interest, interestUpdateDTO);
            return interest;
        }).collect(Collectors.toList());

        return interestRepository.saveAll(interests).stream()
                .map(interestMapper::toInterestResultDTO)
                .collect(Collectors.toList());
    }

    @Override
    public InterestResultDTO getOne(Long id) {
        return interestMapper.toInterestResultDTO(findOne(id));
    }

    @Override
    public Interest findOne(Long id) {
        return interestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Interest with id: " + id + " not found"));
    }

    @Override
    public Page<InterestResultDTO> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return interestRepository.findAll(pageRequest).map(interestMapper::toInterestResultDTO);
    }

    @Override
    public Interest findInterestByMonth(int month) {
        return interestRepository.findByMonth(month)
                .orElseThrow(() -> new EntityNotFoundException("Interest for month: " + month + " not found"));
    }

    @Override
    public void deleteById(Long id) {
        interestRepository.deleteById(id);
    }

    @Override
    public void interestsFaker() {
        Random random = new Random();
        List<Interest> interests = new ArrayList<>();
        for (int i = 1; i <= 60; i++) {
            interests.add(Interest.builder()
                    .month(i)
                    .rate(BigDecimal.valueOf(random.nextFloat() + 1))
                    .build());
        }
        interestRepository.saveAll(interests);
    }
}
