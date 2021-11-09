package zw.co.mynhaka.polad.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.mynhaka.polad.domain.enums.PersonType;
import zw.co.mynhaka.polad.domain.enums.Term;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;


@Table(
        uniqueConstraints = @UniqueConstraint(
                name = "uk_name_sum_assured_premium_person_type",
                columnNames = {
                        "name",
                        "sumAssured",
                        "premium",
                        "personType",
                        "term"
                }
        )
)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ComprehensiveFuneralProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double sumAssured;

    private double premium;

    @Enumerated(EnumType.STRING)
    private PersonType personType;

    @Enumerated(EnumType.STRING)
    private Term term;

    @ManyToOne(fetch = FetchType.LAZY)
    private ComprehensiveFuneral comprehensiveFuneral;

    private int clawbackPeriod;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComprehensiveFuneralProduct)) return false;
        ComprehensiveFuneralProduct that = (ComprehensiveFuneralProduct) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getSumAssured(), that.getSumAssured()) &&
                Objects.equals(getPremium(), that.getPremium()) &&
                getPersonType() == that.getPersonType() &&
                getTerm() == that.getTerm();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSumAssured(), getPremium(), getPersonType(), getTerm());
    }

    @Override
    public String toString() {
        return "ComprehensiveFuneralProduct{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sumAssured=" + sumAssured +
                ", premium=" + premium +
                ", personType=" + personType +
                ", term=" + term +
                '}';
    }
}
