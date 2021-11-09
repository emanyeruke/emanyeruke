package zw.co.mynhaka.policyholderservice.domain.dto.policyholder;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import zw.co.mynhaka.policyholderservice.domain.dto.address.AddressCreateDto;
import zw.co.mynhaka.policyholderservice.domain.enums.Gender;
import zw.co.mynhaka.policyholderservice.domain.enums.PremiumPayer;
import zw.co.mynhaka.policyholderservice.domain.enums.Title;
import zw.co.mynhaka.policyholderservice.validation.IdNumberConstraint;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class PolicyHolderUpdateDTO {
    private long policyHolderId;

    @NotNull(message = "Please select title")
    private Title title;

    @Size(max = 32)
    private String firstname;

    @Size(max = 32)
    private String lastname;

    @Past
    @DateTimeFormat( pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;

    @IdNumberConstraint
    private String idNumber;

    private Gender gender;

    private String workTelephone;

    private String mobile;

    @Email
    private String email;

    @Size(max = 32)
    private String employeeNumber;

    private String occupation;

    AddressCreateDto physicalAddress;

    AddressCreateDto postalAddress;

    private Long employerId;

    private PremiumPayer premiumPayer;
}