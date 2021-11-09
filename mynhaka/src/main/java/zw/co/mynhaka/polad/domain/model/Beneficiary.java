package zw.co.mynhaka.polad.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.mynhaka.polad.domain.enums.Gender;
import zw.co.mynhaka.polad.domain.enums.PersonType;

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

    private double percentageShare;

    @Enumerated(EnumType.STRING)
    private PersonType personType = PersonType.BENEFICIARY;

    private String policyNumber;

    private double sumAssured; //get from the policy


    @ManyToOne
    private Policy policy;


    //get logic to calculate sum assured
    public double calculateSumAssured(double sumAssured){
        //validate who gets a calculated percentage.
        if (personType.equals(PersonType.EXTENDED_FAMILY) || personType.equals(PersonType.BENEFICIARY)){
            return  sumAssured = percentageShare* policy.getSumAssured(); //have percentage share
        }if (personType.equals(PersonType.PARENT)){
            return sumAssured = percentageShare* policy.getSumAssured(); //have a percentage share
        }else
            return  sumAssured= policy.getSumAssured(); //have same amount as policy holder for child and spouse

    }
}
