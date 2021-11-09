package zw.co.mynhaka.polad.reports;

import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;

public interface JasperReportGenerator {

    Resource generateReport(JJasperReportDTO reportRequest);

    InputStream getLogo() throws IOException;
}
