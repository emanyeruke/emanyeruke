package zw.co.mynhaka.policyholderservice.domain.dto.policyholder;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import zw.co.mynhaka.policyholderservice.domain.dto.address.AddressResultDTO;
import zw.co.mynhaka.policyholderservice.domain.dto.employer.EmployerResultDTO;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PolicyHolderResultDTO {

    private Long id;

    private String title;

    private String firstname;

    private String lastname;

    @DateTimeFormat( pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;

    private String idNumber;

    private String gender;

    private String workTelephone;

    private String mobile;

    private String email;

    private EmployerResultDTO employer;

    private String employeeNumber;

    private String occupation;

    private BigDecimal balance;

    private AddressResultDTO physicalAddress;

    private AddressResultDTO postalAddress;

    private String personType;
}