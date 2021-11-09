package zw.co.mynhaka.policyholderservice.domain.dto;

import lombok.Data;

@Data
public class ManagerResultDTO {
    private Long id;

    private String name;

    private String surname;

    private String email;

    private String branch;
}
