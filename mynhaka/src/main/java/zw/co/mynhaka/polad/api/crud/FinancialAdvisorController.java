package zw.co.mynhaka.polad.api.crud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.mynhaka.polad.api.response.ApiResponse;
import zw.co.mynhaka.polad.domain.dtos.financialadvisor.FinancialAdvisorCreateDto;
import zw.co.mynhaka.polad.domain.dtos.financialadvisor.FinancialAdvisorResultDTO;
import zw.co.mynhaka.polad.domain.dtos.financialadvisor.FinancialAdvisorUpdateDto;
import zw.co.mynhaka.polad.domain.model.FinancialAdvisor;
import zw.co.mynhaka.polad.service.iface.FinancialAdvisorService;

import javax.validation.Valid;

@Slf4j

@RestController
@RequestMapping(value = "api/v1/financial-advisor", produces = MediaType.APPLICATION_JSON_VALUE)
public class FinancialAdvisorController {

    private final FinancialAdvisorService financialAdvisorService;

    public FinancialAdvisorController(FinancialAdvisorService financialAdvisorService) {
        this.financialAdvisorService = financialAdvisorService;
    }

    @PostMapping("add")
    public ApiResponse addFinancialAdvisor(@Valid @RequestBody FinancialAdvisorCreateDto financialAdvisorCreateDto){
        return new ApiResponse(200,"SUCCESS", financialAdvisorService.save(financialAdvisorCreateDto));
    }

    @GetMapping("edit/{id}")
    public ApiResponse updateFinancialAdvisor(@PathVariable("id")Long id,
                                           @Valid @RequestBody FinancialAdvisorUpdateDto financialAdvisorUpdateDto){

        FinancialAdvisor oldRecord = financialAdvisorService.getOne(id);

        return new ApiResponse(200,"SUCCESS", financialAdvisorService.update(financialAdvisorUpdateDto));
    }

    @GetMapping("get-financial-advisors/{page}/{size}")
    public ResponseEntity<Page<FinancialAdvisorResultDTO>> findAll(@PathVariable("page") int page, @PathVariable("size") int size) {
        return ResponseEntity.ok(financialAdvisorService.findAll(PageRequest.of(page, size)));
    }

    @GetMapping("/get-financial-advisor/{id}")
    public ResponseEntity<FinancialAdvisorResultDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(financialAdvisorService.findById(id));
    }

    @DeleteMapping("/delete-financial-advisor/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        financialAdvisorService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
