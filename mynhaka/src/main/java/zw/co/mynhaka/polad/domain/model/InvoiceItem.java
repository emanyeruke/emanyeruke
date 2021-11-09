package zw.co.mynhaka.polad.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import zw.co.mynhaka.polad.domain.enums.PolicyType;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * An InvoiceItem
 */

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedEntityGraph(
        name = "InvoiceItem.invoice",
        attributeNodes = @NamedAttributeNode("invoice")
)
@Entity
public class InvoiceItem extends AbstractAuditingEntity {

    private static final long serialVersionUID = 1794102871602873298L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "policyholder", length = 64)
    private String policyholder;

    @Column(name = "beneficiary", length = 64)
    private String beneficiary;

    @Column(name = "product", length = 32, nullable = false)
    private String product;

    @Column(name = "price", nullable = false)
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    private Invoice invoice;

    private String policyNumber;

    @Enumerated(EnumType.STRING)
    private PolicyType policyType;

    @Override
    public String toString() {
        return "InvoiceItem{" +
                "id=" + id +
                ", policyholder='" + policyholder + '\'' +
                ", beneficiary='" + beneficiary + '\'' +
                ", product='" + product + '\'' +
                ", price=" + price +
                ", invoice=" + invoice +
                ", policyNumber='" + policyNumber + '\'' +
                ", policyType=" + policyType +
                '}';
    }

    public InvoiceItem(String policyholder, String beneficiary, String product, double price) {
        this.beneficiary = beneficiary;
        this.policyholder = policyholder;
        this.product = product;
        this.price = price;
    }

    public void addInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}
