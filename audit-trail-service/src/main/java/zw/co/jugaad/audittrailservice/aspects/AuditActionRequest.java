package zw.co.jugaad.audittrailservice.aspects;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AuditActionRequest {

    private String username;

    private String resource;

    private String actionPerformed;

    private LocalDateTime dateTime;

    private String clientIpAddress;

    private String serverIpAddress;

    private String payload;

    private String userAgent;
}
