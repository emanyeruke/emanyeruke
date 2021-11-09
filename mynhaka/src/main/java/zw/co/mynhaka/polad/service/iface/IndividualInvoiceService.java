package zw.co.mynhaka.polad.service.iface;

import net.sf.jasperreports.engine.JRException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import zw.co.mynhaka.polad.domain.dtos.invoice.InvoiceResultDTO;
import zw.co.mynhaka.polad.domain.dtos.invoice.InvoiceUpdateDTO;
import zw.co.mynhaka.polad.domain.model.Invoice;
import zw.co.mynhaka.polad.domain.model.PolicyHolder;
import zw.co.mynhaka.polad.service.exception.EntityNotFoundException;

import java.io.FileNotFoundException;
import java.util.Optional;

public interface IndividualInvoiceService {

    InvoiceResultDTO save(final Long id) throws EntityNotFoundException, FileNotFoundException, JRException;

    InvoiceResultDTO individualInvoice(final Long id) throws EntityNotFoundException, FileNotFoundException, JRException;

    InvoiceResultDTO save(final InvoiceUpdateDTO invoiceUpdateDTO);

    Page<InvoiceResultDTO> findAll(final Pageable pageable);

    Optional<Invoice> findOne(final Long id);

    Invoice getOne(final Long id);

    InvoiceResultDTO getOneDto(final Long id);

    void delete(final Long id);

    void generateIndividualInvoices(PolicyHolder policyHolder) throws FileNotFoundException, JRException;
}
