package zw.co.mynhaka.polad.domain.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
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
        name = "BeneficiaryFuneral.funeral",
        attributeNodes = @NamedAttributeNode("funeral")
)
public class BeneficiaryFuneral {


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

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;

    private long percentageShare = 0L;

    @Enumerated(EnumType.STRING)
    private PersonType personType;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "funeral_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private FuneralProduct funeral;

    @ManyToOne(fetch = FetchType.LAZY)
    PolicyFuneral policyFuneral;

    private String policyNumber;

    private double sumAssured; //get from the policy

}
