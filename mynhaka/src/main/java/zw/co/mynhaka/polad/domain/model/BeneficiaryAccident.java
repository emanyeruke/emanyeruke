package zw.co.mynhaka.polad.domain.model;

import lombok.*;
import zw.co.mynhaka.polad.domain.enums.Gender;
import zw.co.mynhaka.polad.domain.enums.PersonType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedEntityGraph(
        name = "BeneficiaryAccident.accidentProduct",
        attributeNodes = @NamedAttributeNode("accident")
)
public class BeneficiaryAccident {

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

    private double percentageShare;

    @Enumerated(EnumType.STRING)
    private PersonType personType;


    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "accident_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private AccidentProduct accident;

    @ManyToOne(fetch = FetchType.LAZY)
    PolicyAccident policyAccident;


    private String policyNumber;

    private double sumAssured; //get from the policy

    //get logic to calculate sum assured
    public double getSumAssured(double percentageShare){
        //validate who gets a calculated percentage.
        if (personType==PersonType.EXTENDED_FAMILY  || personType==PersonType.BENEFICIARY){
            sumAssured = percentageShare*policyAccident.getSumAssured(); //have percentage share
        }if (personType==PersonType.PARENT){
            sumAssured = percentageShare*policyAccident.getSumAssured(); //have a percentage share
        }else
            sumAssured= policyAccident.getSumAssured(); //have same amount as policy holder for child and spouse
       return getSumAssured();
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof BeneficiaryAccident)) return false;
        BeneficiaryAccident that = (BeneficiaryAccident) object;
        return getPercentageShare() == that.getPercentageShare() &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getSurname(), that.getSurname()) &&
                Objects.equals(getRelationship(), that.getRelationship()) &&
                Objects.equals(getIdNumber(), that.getIdNumber()) &&
                getGender() == that.getGender() &&
                Objects.equals(getDateOfBirth(), that.getDateOfBirth()) &&
                getPersonType() == that.getPersonType() &&
                Objects.equals(getPolicyNumber(), that.getPolicyNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSurname(), getRelationship(), getIdNumber(), getGender(), getDateOfBirth(), getPercentageShare(), getPersonType(), getPolicyNumber());
    }
}
