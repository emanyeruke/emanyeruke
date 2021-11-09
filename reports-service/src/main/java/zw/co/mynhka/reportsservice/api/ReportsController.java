package zw.co.mynhka.reportsservice.api;


import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

@Slf4j
@RestController

@RequestMapping(value = "/api/v1/reports", produces = MediaType.APPLICATION_JSON_VALUE)

public class ReportsController {

    @Autowired
    DataSource dataSource;

    @GetMapping("/policyholders")
    public void getFile() throws JRException, SQLException {

       /* Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/mynhakamylife","root","Venon?1986"
        );
*/
        JasperReport jasperReport = JasperCompileManager.compileReport(
                "/home/cap10mycap10/Documents/Projects/myPolad/reports-service/src/main/resources/report-template/Policyholder.jrxml"
        );
        System.out.println("Done compiling");
        /*JRDataSource jrDataSource = new JREmptyDataSource(10);*/
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource.getConnection());
        System.out.println("Done filling the report");
        JasperExportManager.exportReportToPdfFile( jasperPrint, "policyholder.pdf");
        System.out.println("Done filling the report");
    }

    @GetMapping("/accident")
    public void getAccident() throws JRException, SQLException {

       /* Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/mynhakamylife","root","Venon?1986"
        );
*/
        JasperReport jasperReport = JasperCompileManager.compileReport(
                "/home/cap10mycap10/Documents/Projects/myPolad/reports-service/src/main/resources/report-template/Accident Policies.jrxml"
        );
        System.out.println("Done compiling");
        /*JRDataSource jrDataSource = new JREmptyDataSource(10);*/
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource.getConnection());
        System.out.println("Done filling the report");
        JasperExportManager.exportReportToPdfFile( jasperPrint, "accident.pdf");
        System.out.println("Done filling the report");
    }


    @GetMapping("/comprehensive/{gender}/{policy_status}")
    public void getComprehensive(@PathVariable("gender") String gender,
                                 @PathVariable("policy_status") String policy_status) throws JRException, SQLException {
        HashMap params = new HashMap();
        params.put("FILTER_GENDER", gender);
        params.put("FILTER_POLICY_STATUS", policy_status);
       /* Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/mynhakamylife","root","Venon?1986"
        );
*/
        JasperReport jasperReport = JasperCompileManager.compileReport(
                "/home/cap10mycap10/Documents/Projects/myPolad/reports-service/src/main/resources/report-template/ComprehensivePolicies.jrxml"
        );
        System.out.println("Done compiling");
        /*JRDataSource jrDataSource = new JREmptyDataSource(10);*/
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource.getConnection());
        System.out.println("Done filling the report");
        JasperExportManager.exportReportToPdfFile( jasperPrint, "comprehensive.pdf");
        System.out.println("Done filling the report");
    }



    @GetMapping("/invoice/{invoiceid}")
    public void getInvoice(@PathVariable("invoiceid") Long invoiceid) throws JRException, SQLException {

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/mynhakamylife","root","Venon?1986"
        );
        HashMap params = new HashMap();
        params.put("FILTER_INVOICE_NUMBER", invoiceid);

        JasperReport jasperReport = JasperCompileManager.compileReport(
                "/home/cap10mycap10/Documents/Projects/myPolad/reports-service/src/main/resources/report-template/IndividualInvoice.jrxml"
        );
        System.out.println("Done compiling");
        /*JRDataSource jrDataSource = new JREmptyDataSource(10);*/
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource.getConnection());
        System.out.println("Done filling the report");
        JasperExportManager.exportReportToPdfFile( jasperPrint, "invoice.pdf");
        System.out.println("Done filling the report");
    }
}
