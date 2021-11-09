package zw.co.mynhaka.polad.domain.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class LegalGuardian {


    @Id
    private Long id;

    private String name;

    private String surname;

    private String contactNumber;

    private String email;

    private LocalDate dateOfBirth;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private PolicyHolder policyHolder;

}
