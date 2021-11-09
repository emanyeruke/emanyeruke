package zw.co.mynhaka.policyholderservice.domain.dto;

import lombok.Data;

@Data
public class RepresentativeResultDTO {
    private Long representativeId;

    private String firstName;

    private String lastName;

    private String email;

    private String contactNumber;

    private String category;

    private String employer;
}
