package zw.co.mynhaka.polad.service.contracts;

import net.sf.jasperreports.engine.JRException;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

public interface ExportReportService {

    ResponseEntity<Resource> generatePoliciesReport(HttpServletRequest servletRequest, String reportFormat) throws SQLException, IOException, JRException;

    ResponseEntity<Resource> generatePolicyDocument(HttpServletRequest servletRequest, String policyNumber, String reportFormat) throws SQLException, IOException, JRException;

}
