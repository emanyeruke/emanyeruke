package zw.co.mynhaka.policyservice.service.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiExceptionResponse {
    private int statusCode;
    private String message;
    private String timestamp;
    private HttpStatus status;

    public ApiExceptionResponse(String message, String timestamp, HttpStatus status) {
        this.statusCode = status.value();
        this.message = message;
        this.timestamp = timestamp;
        this.status = status;
    }
}
