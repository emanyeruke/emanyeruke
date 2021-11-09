package zw.co.mynhaka.policyholderservice.domain.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ManagerUpdateDTO {
    @NotNull
    private Long managerId;

    @Size(min = 5, max = 50)
    private String name;

    @Size(min = 5, max = 50)
    private String surname;

    @Email
    private String email;

    private String branch;
}
