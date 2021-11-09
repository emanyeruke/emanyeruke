package zw.co.mynhaka.polad.api.crud;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import zw.co.mynhaka.polad.domain.dtos.invoice.InvoiceResultDTO;
import zw.co.mynhaka.polad.service.iface.InvoiceService;

import java.io.FileNotFoundException;
import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/invoice", produces = MediaType.APPLICATION_JSON_VALUE)
public class InvoiceController {

    private final InvoiceService invoiceService;

    @GetMapping("/get-all-invoices/{page}/{size}")
    public ResponseEntity<Page<InvoiceResultDTO>> getAllInvoices(@PathVariable("page") int page, @PathVariable("size") int size) {
        return ResponseEntity.ok(invoiceService.findAll(PageRequest.of(page, size)));
    }

    @GetMapping("invoice/{id}")
    public ResponseEntity<InvoiceResultDTO> getInvoice(@PathVariable final Long id) {
        log.debug("REST request to get an invoice : {}", id);
        return ResponseEntity.ok(invoiceService.getOneDto(id));
    }


    @GetMapping("policyholder/invoice-idnum/{idnumber}")
    public ResponseEntity<List<InvoiceResultDTO>> getInvoicebyPolicyIdNumber(@PathVariable final String idnumber) {
        log.debug("REST request to get an invoice for Policyholder id : {}", idnumber);
        return ResponseEntity.ok(invoiceService.getByIdNumber(idnumber));
    }

    @GetMapping("policyholder/invoice-policynum/{policyNumber}")
    public ResponseEntity<List<InvoiceResultDTO>> getInvoicebyPolicyNumber(@PathVariable final String policyNumber) {
        log.debug("REST request to get an invoice for Policyholder id : {}", policyNumber);
        return ResponseEntity.ok(invoiceService.getByPolicyNumber(policyNumber));
    }

    @PostMapping("/policyholder/{id}")
    public ResponseEntity<InvoiceResultDTO> generatePolicyHolderInvoice(@PathVariable final Long id) throws FileNotFoundException, JRException {
        log.debug("REST request to generate an invoice : {}", id);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri)
                .body(invoiceService.individualInvoice(id));
    }

    @PostMapping("/employer/{id}")
    public ResponseEntity<InvoiceResultDTO> generateEmployerInvoice(@PathVariable final Long id) throws FileNotFoundException, JRException {
        log.debug("REST request to generate an invoice : {}", id);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri)
                .body(invoiceService.employerInvoice(id));
    }

    @DeleteMapping("invoice/{id}")
    public ResponseEntity deleteInvoice(@PathVariable("id") Long id) {
        invoiceService.delete(id);
        return ResponseEntity.noContent().build();
    }

}



