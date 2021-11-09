package zw.co.mynhaka.policyholderservice.domain.dto.policyholder;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import zw.co.mynhaka.policyholderservice.domain.dto.address.AddressCreateDto;
import zw.co.mynhaka.policyholderservice.domain.enums.Gender;
import zw.co.mynhaka.policyholderservice.domain.enums.PremiumPayer;
import zw.co.mynhaka.policyholderservice.domain.enums.Title;
import zw.co.mynhaka.policyholderservice.validation.IdNumberConstraint;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class PolicyHolderCreateDTO {
    @NotNull(message = "Please select title")
    private Title title;

    @Size(min = 1, max = 32)
    private String firstname;

    @Size(min = 1, max = 32)
    private String lastname;

    @Past(message = "Date should be in the past")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;

    @IdNumberConstraint(message = "ID format incorrect")
    private String idNumber;

    @NotNull(message = "Please select gender")
    private Gender gender;

    private String workTelephone;

    private String mobile;

    @Email(message = "Invalid email")
    @Size(max = 32)
    private String email;

    @Size(max = 32)
    private String employeeNumber;

    @Size(max = 50)
    private String occupation;

    private @Valid
    AddressCreateDto physicalAddress;

    private @Valid
    AddressCreateDto postalAddress;

    private Long employerId;

    private PremiumPayer premiumPayer;
}
