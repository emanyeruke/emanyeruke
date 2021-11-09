package zw.co.mynhaka.policyholderservice.domain.dto;

import lombok.Data;
import zw.co.mynhaka.policyholderservice.domain.enums.Category;

@Data
public class RepresentativeCreateDTO {
    private String firstName;

    private String lastName;

    private String email;

    private String contactNumber;

    private Category category;

    private Long employerId;
}
