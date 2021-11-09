package zw.co.mynhaka.polad.service.iface;

import net.sf.jasperreports.engine.JRException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import zw.co.mynhaka.polad.domain.dtos.invoice.InvoiceResultDTO;
import zw.co.mynhaka.polad.domain.dtos.invoice.InvoiceUpdateDTO;
import zw.co.mynhaka.polad.domain.model.Employer;
import zw.co.mynhaka.polad.domain.model.Invoice;
import zw.co.mynhaka.polad.domain.model.PolicyHolder;
import zw.co.mynhaka.polad.service.exception.EntityNotFoundException;

import java.io.FileNotFoundException;
import java.util.List;

public interface InvoiceService {

    InvoiceResultDTO save(final Long id) throws EntityNotFoundException, FileNotFoundException, JRException;

    InvoiceResultDTO employerInvoice(final Long id) throws EntityNotFoundException, FileNotFoundException, JRException;

    InvoiceResultDTO individualInvoice(final Long id) throws EntityNotFoundException, FileNotFoundException, JRException;

    InvoiceResultDTO save(final InvoiceUpdateDTO invoiceUpdateDTO);

    Page<InvoiceResultDTO> findAll(final Pageable pageable);

    List<Invoice> findAll();

    Invoice findOne(final Long id);

    Invoice getOne(final Long id);

    InvoiceResultDTO getOneDto(final Long id);

    void delete(final Long id);

    void generateEmployerInvoices(Employer employer) throws FileNotFoundException, JRException;

    void generateIndividualInvoices(PolicyHolder policyHolder) throws FileNotFoundException, JRException;

    List<InvoiceResultDTO> getByIdNumber(String idnumber);


    List<InvoiceResultDTO> getByPolicyNumber(String policyNumber);

    Invoice getUnpaidInvoiceByPolicyHolder(PolicyHolder policyHolder);
}
