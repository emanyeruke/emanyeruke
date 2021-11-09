package zw.co.mynhaka.polad.domain.dtos.accident;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import zw.co.mynhaka.polad.domain.enums.Gender;
import zw.co.mynhaka.polad.validation.IdNumberConstraint;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Optional;

@Data
public class DeathBeneficiaryUpdateDto {

    @Size(min = 3, max = 50)
    private String name;


    @Size(min = 3, max = 50)
    private String surname;


    private String relationship;

    @IdNumberConstraint
    private Optional<String> idNumber;

    private Gender gender;

    @Past
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Optional<LocalDate> dateOfBirth;


    @Min(0)
    @Max(100)
    private long percentageShare;


    private Long policyAccidentId;

    private Long beneficiaryId;
}
