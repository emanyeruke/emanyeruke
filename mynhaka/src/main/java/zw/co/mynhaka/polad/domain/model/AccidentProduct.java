package zw.co.mynhaka.polad.domain.model;


import lombok.*;
import zw.co.mynhaka.polad.domain.enums.PersonType;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(
        uniqueConstraints = @UniqueConstraint(
                name = "uk_name_sum_assured_premium_person_type",
                columnNames = {
                        "name",
                        "sumAssured",
                        "premium",
                        "personType"
                }
        )
)
@Entity
public class AccidentProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double sumAssured;

    private double premium;

    @Enumerated(EnumType.STRING)
    private PersonType personType;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private Accident accident;

    private int clawbackPeriod;

   /* private int gracePeriod =30;

    private int lapsePeriod= 30;*/
}
