package zw.co.mynhaka.polad.domain.dtos.savings;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import zw.co.mynhaka.polad.domain.enums.Gender;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class SavingsBeneficiaryCreateDto {

    @Size(min = 1, max = 50)
    private String name;

    @Size(min = 1, max = 50)
    private String surname;

    @Size(min = 1, max = 50)
    private String relationship;

    private String idNumber;

    private Gender gender;

    @Past
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;

    @Min(0)
    @Max(100)
    private long percentageShare;

    private Long policySavingsId;
}
