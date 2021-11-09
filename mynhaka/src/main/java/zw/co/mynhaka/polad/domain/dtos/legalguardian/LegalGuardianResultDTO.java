package zw.co.mynhaka.polad.domain.dtos.legalguardian;


import lombok.Data;

import java.time.LocalDate;

@Data
public class LegalGuardianResultDTO {

    private Long id;

    private String name;

    private String surname;

    private String contactNumber;

    private String email;

    private LocalDate dateOfBirth;
}
