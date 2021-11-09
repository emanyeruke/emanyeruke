package zw.co.mynhaka.polad.service.impl;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import zw.co.mynhaka.polad.domain.model.Invoice;
import zw.co.mynhaka.polad.reports.invoice.CompanyInvoiceAssembler;
import zw.co.mynhaka.polad.reports.invoice.CompanyInvoiceReportDto;
import zw.co.mynhaka.polad.service.iface.ReportService;
import zw.co.mynhaka.polad.service.exception.FileStorageException;
import zw.co.mynhaka.polad.service.fileservices.FileStorageProperties;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ReportServiceImpl implements ReportService {

    private final Path uploadStorageLocation;
    private final CompanyInvoiceAssembler companyInvoiceAssembler;

    public ReportServiceImpl(FileStorageProperties uploadStorageLocation, CompanyInvoiceAssembler companyInvoiceAssembler) {
        this.uploadStorageLocation = Paths.get(uploadStorageLocation.getUploadDir())
                .toAbsolutePath().normalize();
        this.companyInvoiceAssembler = companyInvoiceAssembler;
        try {
            Files.createDirectories(this.uploadStorageLocation);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.");
        }
    }

    public String exportInvoiceReport(String reportFormat, Invoice invoice) throws FileNotFoundException, JRException {
        String root = this.uploadStorageLocation.toString();
        File file = ResourceUtils.getFile("invoice-test.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(invoice);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(invoiceList);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Venon Mapfunde");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, root + invoice.getId().toString() + invoice.getCreatedDate() + "invoice.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, root + invoice.getId().toString() + invoice.getCreatedDate() + "invoice.pdf");
        }
        return root + invoice.getId().toString() + invoice.getCreatedDate() + "invoice." + reportFormat;
    }

    @Override
    public String exportCompanyInvoiceReport(String reportFormat, Invoice invoice) throws IOException, JRException {

        val companyInvoiceReportDto = companyInvoiceAssembler.assemble(invoice);

        String root = this.uploadStorageLocation.toString();

        log.info("Upload directory is: " + root);
        File file = ResourceUtils.getFile("invoice.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        List<CompanyInvoiceReportDto> invoiceList = new ArrayList<>();
        invoiceList.add(companyInvoiceReportDto);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(invoiceList);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Nhaka Life");
        parameters.put("logo", getLogo());

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, root + "/" + companyInvoiceReportDto.getId().toString() + "-" +
                    companyInvoiceReportDto.getCreatedDate() + "_" + "invoice.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, root + "/" + companyInvoiceReportDto.getId().toString() + "-" + companyInvoiceReportDto.getCreatedDate() + "_" + "invoice.pdf");
        }
        return root + companyInvoiceReportDto.getId().toString() + companyInvoiceReportDto.getCreatedDate() + "invoice." + reportFormat;
    }

    public InputStream getLogo() throws IOException {
        ClassPathResource resource = new ClassPathResource("nhakaLogo.png");
        return resource.getInputStream();
    }

}