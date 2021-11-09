package zw.co.mynhaka.policyholderservice.domain.dto.address;

import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class AddressCreateDto implements Serializable {
    private static final long serialVersionUID = 8623685153405415312L;

    @Size(max = 32)
    private String street;

    @Size(max = 32)
    private String suburb;

    @Size(max = 32)
    private String city;
}
