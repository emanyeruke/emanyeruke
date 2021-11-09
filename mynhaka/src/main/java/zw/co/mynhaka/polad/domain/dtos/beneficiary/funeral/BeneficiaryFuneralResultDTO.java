package zw.co.mynhaka.polad.domain.dtos.beneficiary.funeral;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeneficiaryFuneralResultDTO {

    private Long beneficiaryId;

    private String name;

    private String surname;

    private String relationship;

    private String idNumber;

    private String gender;

    @Past
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;

    private double percentageShare;

    private Long funeralId;

    private String funeralPolicyNumber;

    private String personType;


}
