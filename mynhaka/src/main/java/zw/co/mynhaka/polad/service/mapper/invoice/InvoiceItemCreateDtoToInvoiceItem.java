package zw.co.mynhaka.polad.service.mapper.invoice;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.invoice.InvoiceItemCreateDTO;
import zw.co.mynhaka.polad.domain.model.InvoiceItem;


@Component
public class InvoiceItemCreateDtoToInvoiceItem implements Converter<InvoiceItemCreateDTO, InvoiceItem> {
    @Override
    public InvoiceItem convert(InvoiceItemCreateDTO invoiceItemCreateDTO) {
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
