package zw.co.mynhaka.polad.reports;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.polad.service.exception.InvalidRequestException;


import java.io.*;
import java.nio.file.Files;

@Slf4j
@Service
public class JasperReportGeneratorImpl implements JasperReportGenerator {


    @Override
    public InputStream getLogo() throws IOException {
        ClassPathResource resource = new ClassPathResource("reports/logo.png");
        return resource.getInputStream();
    }

    @Override
    public Resource generateReport(JJasperReportDTO request) {

        try {
            val fileExtension = request.getReportFormat().getFileExtension();
            val bytes = compileReport(request);
            val tempFile = Files.createTempFile("report", fileExtension);
            try (OutputStream outputStream = new FileOutputStream(tempFile.toFile())) {
                outputStream.write(bytes);
            }
            Resource resource = new UrlResource(tempFile.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("File not found");
            }
        } catch (IOException | JRException e) {
            log.error("#### Failed to generate report due to : {}", e.getMessage());
            throw new InvalidRequestException("Failed to report due to : " + e.getMessage());
        }
    }

    private byte[] compileReport(JJasperReportDTO reportRequest) throws IOException, JRException {
        val reportName = reportRequest.getReportName();
        val parameters = reportRequest.getParameters();
//        parameters.put("rbz_logo", getLogo());
        val source = reportRequest.getSource();
        val reportFormat = reportRequest.getReportFormat();
        ClassPathResource resource = new ClassPathResource("reports/" + reportName + ".jrxml");
        InputStream file = resource.getInputStream();
        JasperReport jasperReport = JasperCompileManager.compileReport(file);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
        return exportReport(jasperPrint, reportFormat);
    }

    private byte[] exportReport(JasperPrint jasperPrint, ReportFormat reportFormat) throws JRException, IOException {
        switch (reportFormat) {
            case PDF:
                return JasperExportManager.exportReportToPdf(jasperPrint);
            case XLSX:
                return exportToExcel(jasperPrint);
            case DOCX:
                return exportToDocx(jasperPrint);
            case CSV:
                return exportToCsv(jasperPrint);
            default:
                throw new InvalidRequestException("Report format not supported");
        }
    }

    private byte[] exportToExcel(JasperPrint jasperPrint) throws JRException, IOException {
        val tempFile = File.createTempFile("report", ".xlsx");
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(tempFile));
        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
        configuration.setOnePagePerSheet(true);
        configuration.setRemoveEmptySpaceBetweenColumns(true);
        configuration.setDetectCellType(true);
        exporter.setConfiguration(configuration);
        exporter.exportReport();
        return Files.readAllBytes(tempFile.toPath());
    }

    private byte[] exportToDocx(JasperPrint jasperPrint) throws JRException, IOException {
        JRDocxExporter exporter = new JRDocxExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        val tempFile = File.createTempFile("report", ".docx");
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(tempFile));
        exporter.exportReport();
        return Files.readAllBytes(tempFile.toPath());

    }

    public byte[] exportToCsv(JasperPrint jasperPrint) throws JRException, IOException {
        val tempFile = File.createTempFile("report", ".csv");
        JRCsvExporter exporter = new JRCsvExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleWriterExporterOutput(tempFile));
        SimpleCsvExporterConfiguration configuration = new SimpleCsvExporterConfiguration();
        configuration.setWriteBOM(Boolean.TRUE);
        configuration.setRecordDelimiter("\r\n");
        exporter.setConfiguration(configuration);
        exporter.exportReport();
        return Files.readAllBytes(tempFile.toPath());
    }

}
