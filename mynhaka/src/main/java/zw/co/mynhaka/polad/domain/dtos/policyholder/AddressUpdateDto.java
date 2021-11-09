package zw.co.mynhaka.polad.domain.dtos.policyholder;

import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class AddressUpdateDto implements Serializable {
    private static final long serialVersionUID = 8623685153405415312L;

    private long policyHolderId;


    @Size(max = 32)
    private String street;


    @Size(max = 32)
    private String suburb;


    @Size(max = 32)
    private String city;
}