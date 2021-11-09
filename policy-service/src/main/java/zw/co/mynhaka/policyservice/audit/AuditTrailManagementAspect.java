package zw.co.mynhaka.policyservice.audit;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Component
@Aspect
public class AuditTrailManagementAspect {


    private final AuditFeignClientService auditFeignClientService;



    public AuditTrailManagementAspect(AuditFeignClientService auditFeignClientService) {
        this.auditFeignClientService = auditFeignClientService;
    }

    @Around(value = "@annotation(audit)", argNames = "joinPoint,audit")
    public Object handleAuditTrail(final ProceedingJoinPoint joinPoint, final Audit audit) throws Throwable {

        log.info("## action: {} resource: {}", audit.action(), audit.resource());

        String clientIPAddress = null;

        String serverIPAddress = null;

        String userAgent = null;

        String username = null;

        String payload = null;

        try {

            payload = Arrays.toString(joinPoint.getArgs());

            log.info("## payload {}", payload);

            HttpServletRequest request =
                    ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                            .getRequest();

            clientIPAddress = request.getHeader("X-FORWARDED-FOR");

            if (clientIPAddress == null) {

                clientIPAddress = Optional.ofNullable(request.getRemoteAddr())
                        .orElse(request.getLocalAddr());
            } else {

                clientIPAddress = clientIPAddress.contains(",") ? clientIPAddress.split(",")[0] : clientIPAddress;
            }

            serverIPAddress = request.getLocalAddr();

            username = getUsername();


            userAgent = request.getHeader("User-Agent");

            Object result = joinPoint.proceed();

            log.info("The method {}() ends with {}", joinPoint.getSignature().getName(), result);

            return result;
        } catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        } finally {
            String finalClientIPAddress = clientIPAddress;
            String finalServerIPAddress = serverIPAddress;
            String finalUsername = username;
            String finalPayload = payload;
            String finalUserAgent = userAgent;

            try {
                new Thread(() -> {
                    val auditActionRequest = AuditActionRequest.builder()
                            .actionPerformed(audit.action())
                            .resource(audit.resource())
                            .clientIpAddress(finalClientIPAddress)
                            .userAgent(finalUserAgent)
                            .dateTime(LocalDateTime.now())
                            .serverIpAddress(finalServerIPAddress)
                            .username(finalUsername)
                            .payload(finalPayload)
                            .build();
                    Object response = auditFeignClientService.create(auditActionRequest);

                    /*RestTemplate restTemplate = new RestTemplate();
                    Object response = restTemplate.postForObject("https://api-akupay.jugaad.co.zw/akupay-audit-trail-service/api/v1/audits/create",
                            auditActionRequest, Object.class);*/

                    log.info("### response {}", response);
                }).start();
            } catch (Exception ex) {
                log.error("----> Error on saving audit trail {}", ex.getMessage());
            }
        }
    }

    private String getUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }

}
