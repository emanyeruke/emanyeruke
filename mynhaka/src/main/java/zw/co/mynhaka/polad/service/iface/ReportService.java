package zw.co.mynhaka.polad.service.iface;

import net.sf.jasperreports.engine.JRException;
import zw.co.mynhaka.polad.domain.model.Invoice;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ReportService {
    String exportInvoiceReport(String reportFormat, Invoice invoice) throws FileNotFoundException, JRException;

    String exportCompanyInvoiceReport(String reportFormat, Invoice invoice) throws IOException, JRException;




}
