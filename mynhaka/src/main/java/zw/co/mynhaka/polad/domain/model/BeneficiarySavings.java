package zw.co.mynhaka.polad.domain.model;

import lombok.*;
import zw.co.mynhaka.polad.domain.enums.Gender;
import zw.co.mynhaka.polad.domain.enums.PersonType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedEntityGraph(
        name = "BeneficiarySavings.savings",
        attributeNodes = @NamedAttributeNode("savings")
)
public class BeneficiarySavings {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    private String relationship;

    private String idNumber;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate dateOfBirth;

    private long percentageShare = 0L;

    @Enumerated(EnumType.STRING)
    private PersonType personType;


    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "savings_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private SavingsProduct savings;

    @ManyToOne(fetch = FetchType.LAZY)
    PolicySavings policySavings;

    private String policyNumber;

    private double sumAssured; //get from the policy


}
