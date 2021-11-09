package zw.co.mynhaka.policyholderservice.audit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuditActionRequest {

    private String username;

    private String resource;

    private String userAgent;

    private String actionPerformed;

    private LocalDateTime dateTime;

    private String clientIpAddress;

    private String serverIpAddress;

    private String payload;
}
