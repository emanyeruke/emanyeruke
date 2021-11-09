package zw.co.mynhaka.polad.domain.dtos.employer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.mynhaka.polad.domain.dtos.policyholder.AddressResultDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployerResultDTO {

    private Long id;

    private String name;

   /* private String representative;*/

    private String email;

    private String contactNumber;

    private double balance;

    private AddressResultDTO physicalAddress;

    private AddressResultDTO postalAddress;


}
