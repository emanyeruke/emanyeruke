package zw.co.mynhaka.polad.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import zw.co.mynhaka.polad.domain.enums.InvoiceStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * An Invoice
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedEntityGraph(
        name = "Invoice.invoiceItemSet",
        attributeNodes = {@NamedAttributeNode("invoiceItemSet"),
                @NamedAttributeNode("invoiceItemSet")}
)
@Entity
public class Invoice extends AbstractAuditingEntity {

    private static final long serialVersionUID = -3873452779614219991L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate invoicingDate;

    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "invoice_status", length = 32, nullable = false)
    private InvoiceStatus invoiceStatus;

    @OneToMany(
            mappedBy = "invoice",
            cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    private Set<InvoiceItem> invoiceItemSet = new HashSet<>();

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne //(fetch = FetchType.LAZY)
    private PolicyHolder policyHolder;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne//(fetch = FetchType.LAZY)
    private Employer employer;


    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "invoice"
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonBackReference
    private Set<Payment> payments = new HashSet<>();

    @Column(name = "opening_balance")
    private double openingBalance;

    @Column(name = "closing_balance")
    private double closingBalance;

    private String applicationForm_url;

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", invoicingDate=" + invoicingDate +
                ", dueDate=" + dueDate +
                ", invoiceStatus=" + invoiceStatus +
                ", openingBalance=" + openingBalance +
                ", closingBalance=" + closingBalance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Invoice)) return false;
        if (!super.equals(o)) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(getId(), invoice.getId()) &&
                Objects.equals(getInvoicingDate(), invoice.getInvoicingDate()) &&
                Objects.equals(getDueDate(), invoice.getDueDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getInvoicingDate(), getDueDate());
    }
}

