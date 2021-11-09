package zw.co.jugaad.audittrailservice.reports;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.util.Map;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JasperReportDTO {

    private String reportName;

    private Map<String, Object> parameters;

    private ReportFormat reportFormat;

    private Connection source;
}
