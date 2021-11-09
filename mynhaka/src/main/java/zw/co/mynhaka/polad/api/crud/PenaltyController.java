package zw.co.mynhaka.polad.api.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import zw.co.mynhaka.polad.domain.dtos.penalty.PenaltyCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.penalty.PenaltyResultDTO;
import zw.co.mynhaka.polad.domain.dtos.penalty.PenaltyUpdateDTO;
import zw.co.mynhaka.polad.service.iface.PenaltyService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/penalty", produces = MediaType.APPLICATION_JSON_VALUE)
public class PenaltyController {

    private final PenaltyService penaltyService;

    @PostMapping(value = "/add-penalty")
    public PenaltyResultDTO create(@RequestBody @Valid PenaltyCreateDTO penaltyCreateDTO) {
        return penaltyService.save(penaltyCreateDTO);
    }

    @PostMapping(value = "/add-penalties")
    public List<PenaltyResultDTO> create(@RequestBody @Valid List<PenaltyCreateDTO> penaltyCreateDTOS) {
        return penaltyService.save(penaltyCreateDTOS);
    }

    @GetMapping(value = "/get-all-penalties")
    public Page<PenaltyResultDTO> getAllPenalties(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "60") int size
    ) {
        return penaltyService.findAll(page, size);
    }

    @PutMapping("/edit-penalty/{id}")
    public PenaltyResultDTO update(
            @PathVariable("id") long id,
            @RequestBody @Valid PenaltyUpdateDTO penaltyUpdateDTO
    ) {
        return penaltyService.update(id, penaltyUpdateDTO);
    }

    @PutMapping("edit-penalties")
    public List<PenaltyResultDTO> update(@RequestBody @Valid List<PenaltyUpdateDTO> penaltyUpdateDTOS) {
        return penaltyService.update(penaltyUpdateDTOS);
    }

    @GetMapping(value = "/get-penalty/{id}")
    public PenaltyResultDTO getOneById(@PathVariable("id") Long id) {
        return penaltyService.getOne(id);
    }

    @DeleteMapping(value = "/delete-penalty/{id}")
    public void deletePenalty(@PathVariable("id") Long id) {
        penaltyService.deleteById(id);
    }

    @PostMapping("penalties-faker")
    public void penaltiesFaker() {
        penaltyService.penaltyFaker();
    }
}
