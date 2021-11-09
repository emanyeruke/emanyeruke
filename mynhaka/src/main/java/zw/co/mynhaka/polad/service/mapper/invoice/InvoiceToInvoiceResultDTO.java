package zw.co.mynhaka.polad.service.mapper.invoice;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.invoice.InvoiceItemResultDTO;
import zw.co.mynhaka.polad.domain.dtos.invoice.InvoiceResultDTO;
import zw.co.mynhaka.polad.domain.model.Invoice;
import zw.co.mynhaka.polad.domain.model.InvoiceItem;

import java.util.ArrayList;
import java.util.List;

@Component
public class InvoiceToInvoiceResultDTO implements Converter<Invoice, InvoiceResultDTO> {

    private final InvoiceItemToInvoiceItemResultDTO invoiceItemToInvoiceItemResultDTO;

    public InvoiceToInvoiceResultDTO(final InvoiceItemToInvoiceItemResultDTO invoiceItemToInvoiceItemResultDTO) {
        this.invoiceItemToInvoiceItemResultDTO = invoiceItemToInvoiceItemResultDTO;
    }


    @Override
    public InvoiceResultDTO convert(Invoice invoice) {

        InvoiceResultDTO invoiceResultDTO = new InvoiceResultDTO();
        invoiceResultDTO.setId(invoice.getId());
        invoiceResultDTO.setClosingBalance(invoice.getClosingBalance());
        invoiceResultDTO.setDueDate(invoice.getDueDate());
        invoiceResultDTO.setInvoicingDate(invoice.getInvoicingDate());
        invoiceResultDTO.setOpeningBalance(invoice.getOpeningBalance());
        invoiceResultDTO.setInvoiceStatus(invoice.getInvoiceStatus());
        if (invoice.getEmployer() != null) {
            invoiceResultDTO.setCompanyId(invoice.getEmployer().getId());
            invoiceResultDTO.setCompanyName(invoice.getEmployer().getName());
        }
        if (invoice.getPolicyHolder() != null) {
            invoiceResultDTO.setPolicyHolderId(invoice.getPolicyHolder().getId());
            invoiceResultDTO.setPolicyHolderName(invoice.getPolicyHolder().getFirstname() + " " + invoice.getPolicyHolder().getLastname());
        }
        List<InvoiceItemResultDTO> invoiceItemResultDTOS = new ArrayList<>();
        for (InvoiceItem invoiceItem : invoice.getInvoiceItemSet()
        ) {
            InvoiceItemResultDTO itemResultDTO = invoiceItemToInvoiceItemResultDTO.convert(invoiceItem);
            invoiceItemResultDTOS.add(itemResultDTO);
        }
        invoiceResultDTO.setInvoiceItemSet(invoiceItemResultDTOS);
        return invoiceResultDTO;
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return null;
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return null;
    }
}