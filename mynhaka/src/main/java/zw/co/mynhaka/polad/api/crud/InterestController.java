package zw.co.mynhaka.polad.api.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import zw.co.mynhaka.polad.domain.dtos.InterestCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.InterestResultDTO;
import zw.co.mynhaka.polad.domain.dtos.InterestUpdateDTO;
import zw.co.mynhaka.polad.service.iface.InterestService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/interest", produces = MediaType.APPLICATION_JSON_VALUE)
public class InterestController {

    private final InterestService interestService;

    @PostMapping("add-interest")
    public InterestResultDTO create(
            @RequestBody @Valid InterestCreateDTO interestCreateDTO
    ) {
        return interestService.save(interestCreateDTO);
    }

    @PostMapping("add-interests")
    public List<InterestResultDTO> create(
            @RequestBody @Valid List<InterestCreateDTO> interestCreateDTOList
    ) {
        return interestService.save(interestCreateDTOList);
    }

    @PutMapping("edit-interest/{id}")
    public InterestResultDTO update(
            @PathVariable("id") long id,
            @RequestBody @Valid InterestUpdateDTO interestUpdateDTO
    ) {
        return interestService.update(id, interestUpdateDTO);
    }

    @PutMapping("edit-interests")
    public List<InterestResultDTO> update(
            @RequestBody @Valid List<InterestUpdateDTO> interestUpdateDTOList
    ) {
        return interestService.update(interestUpdateDTOList);
    }

    @GetMapping("get-interest/{id}")
    public InterestResultDTO getOne(
            @PathVariable("id") long id
    ) {
        return interestService.getOne(id);
    }

    @GetMapping("get-all-interests")
    public Page<InterestResultDTO> getAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "60") int size
    ) {
        return interestService.findAll(page, size);
    }

    @DeleteMapping("delete-interest/{id}")
    public void delete(
            @PathVariable("id") long id
    ) {
        interestService.deleteById(id);
    }

    @PostMapping("interests-faker")
    public void interestFaker() {
        interestService.interestsFaker();
    }
}
