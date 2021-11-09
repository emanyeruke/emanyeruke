package zw.co.mynhaka.policyservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import zw.co.mynhaka.policyservice.domain.enums.PersonType;

import java.time.LocalDate;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PolicyHolder {
    private Long id;
    private String title;
    private String firstname;
    private String lastname;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate dateOfBirth;
    private String idNumber;
    private String gender;
    private String workTelephone;
    private String mobile;
    private String email;
    private PersonType personType;
}
