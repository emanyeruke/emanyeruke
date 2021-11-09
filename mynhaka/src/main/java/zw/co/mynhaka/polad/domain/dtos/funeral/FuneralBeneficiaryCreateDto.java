package zw.co.mynhaka.polad.domain.dtos.funeral;

import lombok.Data;
import zw.co.mynhaka.polad.domain.enums.Gender;
import zw.co.mynhaka.polad.domain.enums.PersonType;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class FuneralBeneficiaryCreateDto {

    @Size(min = 1, max = 50)
    private String name;

    @Size(min = 1, max = 50)
    private String surname;


    private String idNumber;

    private Gender gender;

    @Past
    private LocalDate dateOfBirth;

    private PersonType personType;

    private Long policyFuneralId;
}
