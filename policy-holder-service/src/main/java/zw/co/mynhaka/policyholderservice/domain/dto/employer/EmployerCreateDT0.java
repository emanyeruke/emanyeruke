package zw.co.mynhaka.policyholderservice.domain.dto.employer;

import lombok.Data;
import zw.co.mynhaka.policyholderservice.domain.dto.address.AddressCreateDto;
import zw.co.mynhaka.policyholderservice.validation.ContactNumberConstraint;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class EmployerCreateDT0 {
    @Size(min = 5, max = 50)
    private String name;


    @Size(min = 5, max = 50)
    private String representative;

    @Email
    private String email;

    @ContactNumberConstraint
    private String contactNumber;

    private AddressCreateDto addressCreateDto;
}
