package zw.co.mynhaka.polad.domain.dtos;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import zw.co.mynhaka.polad.domain.enums.Gender;
import zw.co.mynhaka.polad.domain.enums.PersonType;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class BeneficiaryUpdateDTO {
    @NotNull
    private Long policyId;

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
    private double percentageShare;

    private PersonType personType = PersonType.BENEFICIARY;
}
