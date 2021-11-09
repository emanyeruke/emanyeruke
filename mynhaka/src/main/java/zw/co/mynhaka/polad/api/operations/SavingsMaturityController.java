package zw.co.mynhaka.polad.api.operations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import zw.co.mynhaka.polad.api.response.ApiResponse;
import zw.co.mynhaka.polad.audit.Audit;
import zw.co.mynhaka.polad.domain.dtos.*;
import zw.co.mynhaka.polad.domain.model.SavingsMaturity;
import zw.co.mynhaka.polad.service.iface.SavingsMaturityService;
import zw.co.mynhaka.polad.service.iface.SavingsSurrenderService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/savings/maturity")
public class SavingsMaturityController {

    private final SavingsMaturityService savingsMaturityService;
    private final ModelMapper modelMapper;

    @Autowired
    public SavingsMaturityController(SavingsMaturityService savingsMaturityService, ModelMapper modelMapper) {
        this.savingsMaturityService = savingsMaturityService;
        this.modelMapper = modelMapper;
    }



    @PostMapping("{policyNumber}")
    @Audit(resource = "Savings Policy", action = "Submit Maturity Savings Policy")
    public ApiResponse create(
            @PathVariable("policyNumber") String policyNumber,
            @RequestBody @Valid SavingsMaturityCreateDto savingsMaturityCreateDto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

        SavingsMaturity savingsMaturity = modelMapper.map(savingsMaturityCreateDto,SavingsMaturity.class);

        return new ApiResponse(200,"SUCCESS",savingsMaturityService.save(policyNumber,savingsMaturity));
    }

    @PutMapping("/approve")
    @Audit(resource = "Savings Policy", action = "Approve maturity Savings Policy")
    public ApiResponse approve(@RequestBody @Valid SavingsMaturityUpdateDto savingsMaturityUpdateDto){
        SavingsMaturity savingsMaturity = modelMapper.map(savingsMaturityUpdateDto,SavingsMaturity.class);

        //get old Record
        SavingsMaturity oldRecord = savingsMaturityService.getOne(savingsMaturityUpdateDto.getId());

        return new ApiResponse(200,"SUCCESS",savingsMaturityService.update(savingsMaturity));
    }

    @GetMapping("/all")
    @Audit(resource = "Savings Policy", action = "Get All matured Savings Policy")
    public ApiResponse findAll() {
        return new ApiResponse(200,"SUCCESS",savingsMaturityService.findAll());
    }

    @DeleteMapping("{id}")
    @Audit(resource = "Savings Policy", action = "Delete matured Savings Policy")
    public ApiResponse delete(@PathVariable("id") Long id) {
        savingsMaturityService.deleteById(id);
        return new ApiResponse(200,"SUCCESS");
    }
}

