package zw.co.mynhaka.polad.service.mapper.invoice;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.invoice.InvoiceCreateDTO;
import zw.co.mynhaka.polad.domain.model.Invoice;


@Component
public class InvoiceCreateDtoToInvoice implements Converter<InvoiceCreateDTO, Invoice> {
    @Override
    public Invoice convert(InvoiceCreateDTO invoiceCreateDTO) {
        return null;
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
