package zw.co.jugaad.audittrailservice.reports;

import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;

public interface JasperReportGenerator {

    Resource generateReport(JasperReportDTO reportRequest);

    InputStream getLogo() throws IOException;
}
