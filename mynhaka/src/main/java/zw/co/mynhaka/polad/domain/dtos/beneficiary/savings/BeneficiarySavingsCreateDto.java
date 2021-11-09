package zw.co.mynhaka.polad.domain.dtos.beneficiary.savings;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import zw.co.mynhaka.polad.domain.enums.PersonType;

import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.Optional;

@Data
public class BeneficiarySavingsCreateDto {

    private String name;

    private String surname;

    private String relationship;

    @Past
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Optional<LocalDate> dateOfBirth;

    private double percentageShare;

    private PersonType personType;

    private Long policyHolderId;

    private Long savingsId;


}
