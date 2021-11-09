package zw.co.mynhaka.polad.domain.dtos.accident;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import zw.co.mynhaka.polad.domain.enums.Gender;
import zw.co.mynhaka.polad.domain.enums.PersonType;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class SpouseCreateDto {


    @Size(min = 3, max = 50)
    private String name;


    @Size(min = 3, max = 50)
    private String surname;

    @Past
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;


    private String idNumber;

    private Gender gender;

    private PersonType personType;


    private Long policyAccidentId;


    private Long accidentProductId;


}
