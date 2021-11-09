package zw.co.mynhaka.polad.domain.dtos.comprehensivefuneral;

import lombok.Data;
import zw.co.mynhaka.polad.domain.enums.Gender;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class DeathBeneficiaryDto {
    @Size(min = 1, max = 50)
    private String name;

    @Size(min = 1, max = 50)
    private String surname;

    @Size(min = 1, max = 50)
    private String relationship;


    private String idNumber;

    private Gender gender;

    @Past
    private LocalDate dateOfBirth;

    @Min(0)
    @Max(100)
    private long percentageShare;


    private Long policyComprehensiveId;
}
