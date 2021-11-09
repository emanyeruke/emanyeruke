package zw.co.mynhaka.polad.domain.dtos.legalguardian;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import zw.co.mynhaka.polad.validation.ContactNumberConstraint;

import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class LegalGuardianCreateDto {

    @Size(min = 5, max = 50)
    private String name;

    @Size(min = 5, max = 50)
    private String surname;

    @ContactNumberConstraint
    private String contactNumber;

    @Email
    private String email;

    @Past
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;
}
