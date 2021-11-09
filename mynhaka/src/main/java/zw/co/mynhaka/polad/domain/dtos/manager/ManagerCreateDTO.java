package zw.co.mynhaka.polad.domain.dtos.manager;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class ManagerCreateDTO {

    @Size(min = 3, max = 50)
    private String name;

    @Size(min = 3, max = 50)
    private String surname;

    @Email
    private String email;

    private String branch;

}
