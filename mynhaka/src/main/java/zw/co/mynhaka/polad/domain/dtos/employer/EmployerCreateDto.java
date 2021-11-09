package zw.co.mynhaka.polad.domain.dtos.employer;


import lombok.Data;
import zw.co.mynhaka.polad.domain.dtos.policyholder.AddressCreateDto;
import zw.co.mynhaka.polad.validation.ContactNumberConstraint;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class EmployerCreateDto {


    @Size(min = 3, max = 50)
    private String name;


   /* @Size(min = 3, max = 50)
    private String representative;*/

    @Email
    private String email;

    @ContactNumberConstraint
    private String contactNumber;

    private @Valid
    AddressCreateDto physicalAddress;

    private @Valid
    AddressCreateDto postalAddress;

}
