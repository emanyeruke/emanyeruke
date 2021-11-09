package zw.co.mynhaka.polad.api.crud;


import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.mynhaka.polad.api.response.ApiResponse;
import zw.co.mynhaka.polad.domain.dtos.employer.EmployerCreateDto;
import zw.co.mynhaka.polad.domain.dtos.employer.EmployerResultDTO;
import zw.co.mynhaka.polad.domain.dtos.employer.EmployerUpdateDto;
import zw.co.mynhaka.polad.domain.model.Employer;
import zw.co.mynhaka.polad.service.iface.EmployerService;

import javax.validation.Valid;

@Slf4j
@RestController

@RequestMapping(value = "/api/v1/product/employer", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployerController {

    private final EmployerService employerService;
    private  final ModelMapper modelMapper;

    public EmployerController(EmployerService employerService, ModelMapper modelMapper) {
        this.employerService = employerService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("get-all-employers")
    public ApiResponse findAll() {
        return new ApiResponse(200,"SUCCESS",employerService.findAll());
    }

   /* @GetMapping("employers")
    public ResponseEntity<List<EmployerResultDTO>> findAll() {
        return ResponseEntity.ok(employerService.findAll());
    }

    */

//    @PostMapping("add-employer")
//    public ResponseEntity<EmployerResultDTO> create(@Valid @RequestBody EmployerCreateDto employerCreateDto) {
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
//        return ResponseEntity.created(uri)
//                .body(employerService.save(employerCreateDto));
//    }
    @PostMapping("add-employer")
    public ApiResponse createEmployer(@Valid @RequestBody EmployerCreateDto employerCreateDto) {
        log.info("####### Request to add employer: {}", employerCreateDto.toString());
        //URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        Employer employer = modelMapper.map(employerCreateDto,Employer.class);

        log.info("Request received");

        return new ApiResponse(200,"SUCCESS",employerService.add(employer));
    }


    @PostMapping("faker-employer")
    public ResponseEntity createFaker() {
        employerService.fakerEmployer();
        return ResponseEntity.ok().build();
    }


    @GetMapping("get-employer/{id}")
    public ResponseEntity<EmployerResultDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(employerService.findById(id));
    }

    @PutMapping("edit-employer/{id}")
    public ApiResponse updateEmployer( @PathVariable ("id") Long id,
                               @Valid @RequestBody EmployerUpdateDto employerUpdateDto) {

        Employer employer = modelMapper.map(employerUpdateDto,Employer.class);

        //Update old record
        Employer oldRecord = employerService.getOne(id);

        employer.setId(oldRecord.getId());

       // employer.set(oldRecord.getAddress());

        return new ApiResponse(200,"SUCCESS", employerService.update(employer));
    }
    @DeleteMapping("delete-employer/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        employerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
