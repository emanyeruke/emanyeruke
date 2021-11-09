package zw.co.mynhaka.polad.api.crud;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import zw.co.mynhaka.polad.domain.dtos.invoice.InvoiceResultDTO;
import zw.co.mynhaka.polad.service.iface.IndividualInvoiceService;

import java.io.FileNotFoundException;
import java.net.URI;


@Slf4j
@RestController

@RequestMapping(value = "/api/v1/individualinvoice", produces = MediaType.APPLICATION_JSON_VALUE)
public class IndividualInvoiceController {

    private final IndividualInvoiceService invoiceService;

    public IndividualInvoiceController(final IndividualInvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }


    @PostMapping("/policyholder/{id}")
    public ResponseEntity<InvoiceResultDTO> generatePolicyHolderInvoice(@PathVariable final Long id) throws FileNotFoundException, JRException {
        log.debug("REST request to generate an invoice : {}", id);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri)
                .body(invoiceService.save(id));
    }


}
