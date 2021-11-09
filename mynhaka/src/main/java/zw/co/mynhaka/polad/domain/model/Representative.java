package zw.co.mynhaka.polad.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import zw.co.mynhaka.polad.domain.enums.Category;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Representative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String contactNumber;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
   /* @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude

    */
    private Employer employer;

}
