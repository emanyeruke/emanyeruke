package zw.co.mynhaka.polad.domain.dtos.employer;

import lombok.Data;
import zw.co.mynhaka.polad.domain.dtos.policyholder.AddressCreateDto;
import zw.co.mynhaka.polad.validation.ContactNumberConstraint;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class EmployerUpdateDto {

    //private Long employerId;


    @Size(min = 5, max = 50)
    private String name;

    /*@Size(min = 5, max = 50)
    private String representative;*/

    @Email
    private String email;

    @ContactNumberConstraint
    private String contactNumber;

    AddressCreateDto physicalAddress;

    AddressCreateDto postalAddress;
}
