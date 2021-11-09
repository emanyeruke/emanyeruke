package zw.co.mynhaka.polad.domain.dtos.policyholder;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import zw.co.mynhaka.polad.domain.enums.ClientType;
import zw.co.mynhaka.polad.domain.enums.Gender;
import zw.co.mynhaka.polad.domain.enums.Title;
import zw.co.mynhaka.polad.validation.ContactNumberConstraint;
import zw.co.mynhaka.polad.validation.IdNumberConstraint;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PolicyHolderCreateDto {

    @NotNull(message = "Please select title")
    private Title title;

    @Size(min = 1, max = 32)
    private String firstname;

    @Size(min = 1, max = 32)
    private String lastname;

    @Past(message = "Date should be in the past")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;

   // @IdNumberConstraint(message = "ID format incorrect")
    private String idNumber;

    @NotNull(message = "Please select gender")
    private Gender gender;

    @ContactNumberConstraint(message = "Mobile number incorrect")
    private String workTelephone;

    @ContactNumberConstraint(message = "Mobile number incorrect")
    private String mobile;

    @Email(message = "Invalid email")
    @Size(max = 32)
    private String email;

    @Size(max = 32)
    private String employeeNumber;

    private Long employerId;

    @Size(max = 50)
    private String occupation;

    //private double balance;


    private @Valid
    AddressCreateDto physicalAddress;

    private @Valid
    AddressCreateDto postalAddress;

    @NotNull(message = "Please select client type")
    private ClientType clientType;

}
