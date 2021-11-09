package zw.co.jugaad.audittrailservice.utils;

import model.entity.AuditAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public final class Slf4jLoggingAuditTrailManager {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public void record(final AuditAction auditActionContext) {

        log.info(toString(auditActionContext));
    }

    private String toString(AuditAction auditAction) {
        final StringBuilder builder = new StringBuilder();
        builder.append("Audit trail record BEGIN\n");
        builder.append("=============================================================\n");
        builder.append("WHO: ");
        builder.append(auditAction.getUsername());
        builder.append("\n");
        builder.append("WHAT: ");
        builder.append(auditAction.getPayload());
        builder.append("\n");
        builder.append("ACTION: ");
        builder.append(auditAction.getActionPerformed());
        builder.append("\n");
        builder.append("APPLICATION: ");
        builder.append(auditAction.getResource());
        builder.append("\n");
        builder.append("WHEN: ");
        builder.append(auditAction.getDateTime());
        builder.append("\n");
        builder.append("CLIENT IP ADDRESS: ");
        builder.append(auditAction.getClientIpAddress());
        builder.append("\n");
        builder.append("Audit trail record END\n");
        builder.append("=============================================================");
        builder.append("\n");

        return builder.toString();

    }

}
