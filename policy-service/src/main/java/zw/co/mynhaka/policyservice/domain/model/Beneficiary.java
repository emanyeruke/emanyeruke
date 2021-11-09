package zw.co.mynhaka.policyservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.mynhaka.policyservice.domain.enums.Gender;
import zw.co.mynhaka.policyservice.domain.enums.PersonType;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Beneficiary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    private String relationship;

    private String idNumber;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate dateOfBirth;

    private Long percentageShare = 0L;

    @Enumerated(EnumType.STRING)
    private PersonType personType = PersonType.BENEFICIARY;

    @ManyToOne
    private Policy policy;
}
