package zw.co.mynhaka.polad.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import zw.co.mynhaka.polad.domain.model.Invoice;
import zw.co.mynhaka.polad.domain.model.PolicyHolder;
import zw.co.mynhaka.polad.reports.claims.IInvoiveTotal;

import java.util.Optional;


public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    Optional<Invoice> findByPolicyHolderAndInvoiceStatus(PolicyHolder policyHolder, String status);

    @EntityGraph(value = "Invoice.invoiceItemSet", type = EntityGraph.EntityGraphType.LOAD)
    Invoice getOne(Long id);

    @Query(value = "select invoice.id, invoice.created_date, invoice.opening_balance,invoice_status, invoicing_date, due_date, closing_balance, invoice.employer_id, policy_holder_id from invoice, policy_holder where policy_holder.id_number = ?1 and invoice_status !='PAID' order by invoicing_date desc", nativeQuery = true)
    Page<Invoice> getInvoicesByPolicyHolderId(String idNumber, Pageable pageable);

    @Query(value = "select IFNULL(sum(closing_balance),0) as invoiceTotal\n" +
            "from invoice\n" +
            "where month(created_date) = ?1 and year(created_date) =?2\n" +
            "group by year(created_date), month(created_date);", nativeQuery = true)
    IInvoiveTotal findInvoiceTotalForCurrentMonth(int month, int year);

}
