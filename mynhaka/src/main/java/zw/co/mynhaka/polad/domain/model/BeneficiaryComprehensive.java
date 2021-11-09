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
        name = "BeneficiaryComprehensive.comprehensiveFuneral",
        attributeNodes = @NamedAttributeNode("comprehensiveFuneral")
)
public class BeneficiaryComprehensive {
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
    @JoinColumn(name = "comprehensive_funeral_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ComprehensiveFuneralProduct comprehensiveFuneral;

    @ManyToOne(fetch = FetchType.LAZY)
    PolicyComprehensive policyComprehensive;

    private String policyNumber;

    private double sumAssured; //get from the policy

}

