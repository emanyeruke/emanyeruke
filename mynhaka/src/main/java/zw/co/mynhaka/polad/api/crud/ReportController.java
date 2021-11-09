package zw.co.mynhaka.polad.api.crud;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/report")
@Slf4j
public class ReportController {

    /*private ReportController reportService;

     *//**
     * Constructor dependency injector.
     *
     * @param reportService - report service dependency
     *//*
    public ReportResource(final ReportService reportService) {
        this.reportService = reportService;
    }



    @PostMapping(value = "/pdf", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ByteArrayResource> generateSimplePDFReport(@Valid @RequestBody Report report) {
        log.info("Payload for generating simple PDF report: {}", report);
        try {
            ByteArrayResource byteArrayResource = reportService.generateSimpleReport(report, ExportReportType.PDF);
            return new ResponseEntity<>(byteArrayResource, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping(value = "/docx", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ByteArrayResource> generateSimpleDOCxReport(@Valid @RequestBody Report report) {
        log.info("Payload for generating simple DOCx report: {}", report);
        try {
            ByteArrayResource byteArrayResource = reportService.generateSimpleReport(report, ExportReportType.DOCX);
            return new ResponseEntity<>(byteArrayResource, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping(value = "/data-source", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ByteArrayResource> generateDataSourceReport(@Valid @RequestBody List<Report> reportDataSource) {
        log.info("Payload for generating report with data source: {}", reportDataSource);
        try {
            ByteArrayResource byteArrayResource = reportService.generateDataSourceReport(
                    reportDataSource, ExportReportType.PDF
            );
            return new ResponseEntity<>(byteArrayResource, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }*/
}

