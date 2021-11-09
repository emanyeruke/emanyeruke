package zw.co.mynhaka.polad.api.crud;


import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.mynhaka.polad.api.response.ApiResponse;
import zw.co.mynhaka.polad.domain.dtos.agent.CommissionResultDTO;
import zw.co.mynhaka.polad.domain.dtos.commissionrates.CommissionRatesCreateDto;
import zw.co.mynhaka.polad.domain.dtos.commissionrates.CommissionRatesUpdateDto;
import zw.co.mynhaka.polad.domain.model.Commission;
import zw.co.mynhaka.polad.service.iface.CommissionService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class CommissionRatesController {

    private final CommissionService commissionService;
    private final ModelMapper modelMapper;

    @Autowired
    public CommissionRatesController(CommissionService commissionService, ModelMapper modelMapper) {
        this.commissionService = commissionService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("commission")
    public ApiResponse findAll() {
        return new ApiResponse(200,"SUCCESS",commissionService.findAll());
    }

    @PostMapping("add-commission-rate")
    public ApiResponse addPolicyCommissionRates(@Valid @RequestBody CommissionRatesCreateDto commissionCreateDTO) {
        log.info("####### PROCESSING COMMISSION RATES REQUEST....", commissionCreateDTO.toString());
        Commission commission = modelMapper.map(commissionCreateDTO,Commission.class);

        log.info("################REQUEST RECEIVED.....");
        return new ApiResponse(200,"SUCCESS",commissionService.add(commission));
    }

   /* @PostMapping("faker-commission")
    public ResponseEntity createFaker() {
        commissionService.fakerCommission();
        return ResponseEntity.ok().build();
    }

    */

    @PutMapping("edit/commission-rate/{id}")
    public ApiResponse updatePolicyCommissionRates(@PathVariable("id") Long id,
                              @Valid @RequestBody CommissionRatesUpdateDto commissionUpdateDTO) {
        Commission commission = modelMapper.map(commissionUpdateDTO,Commission.class);

        commissionService.getOne(id);

        return new ApiResponse(200,"SUCCESS",commissionService.update(commission));
    }

    @GetMapping("commission/{id}")
    public ResponseEntity<CommissionResultDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(commissionService.findById(id));
    }

    @DeleteMapping("commission/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        commissionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
