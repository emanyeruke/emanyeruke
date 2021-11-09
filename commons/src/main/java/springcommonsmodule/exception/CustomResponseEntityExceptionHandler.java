package springcommonsmodule.exception;


import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import springcommonsmodule.dto.RestResponse;


import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Slf4j
@ControllerAdvice
@RestController

public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Object> handleFeignStatusException(FeignException e, HttpServletResponse response) {
        log.error(e.getMessage());
        response.setStatus(e.status());
        Map<String, Object> error = new JSONObject(e.contentUTF8()).toMap();
        String message = error.getOrDefault("message", HttpStatus.BAD_REQUEST).toString();
        RestResponse errorDetails = new RestResponse(message, LocalDateTime.now().toString(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(InvalidRequestException.class)
    public final ResponseEntity<RestResponse> handleInvalidRequestException(InvalidRequestException ex, WebRequest request) {
        RestResponse errorDetails = new RestResponse(ex.getMessage(), LocalDateTime.now().toString(), HttpStatus.BAD_REQUEST, 6);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public final ResponseEntity<RestResponse> handleInvalidRequestException(FileNotFoundException ex, WebRequest request) {
        RestResponse errorDetails = new RestResponse(ex.getMessage(), LocalDateTime.now().toString(), HttpStatus.BAD_REQUEST, 6);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileStorageException.class)
    public final ResponseEntity<RestResponse> handleInvalidRequestException(FileStorageException ex, WebRequest request) {
        RestResponse errorDetails = new RestResponse(ex.getMessage(), LocalDateTime.now().toString(), HttpStatus.BAD_REQUEST, 6);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<RestResponse> handleRecordNotFoundException(RecordNotFoundException ex, WebRequest request) {
        RestResponse errorDetails = new RestResponse(ex.getMessage(), LocalDateTime.now().toString(), HttpStatus.NOT_FOUND, 6);
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public final ResponseEntity<RestResponse> handleNoSuchElementException(NoSuchElementException ex, WebRequest request) {
        RestResponse errorDetails = new RestResponse(ex.getMessage(), LocalDateTime.now().toString(), HttpStatus.NOT_FOUND, 6);
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(java.lang.IllegalAccessException.class)
    public final ResponseEntity<RestResponse> handleIllegalAccessException(java.lang.IllegalAccessException ex, WebRequest request) {
        RestResponse errorDetails = new RestResponse(ex.getMessage(), LocalDateTime.now().toString(), HttpStatus.FORBIDDEN, 6);
        return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(IllegalOperationException.class)
    public final ResponseEntity<RestResponse> handleIllegalOperationException(IllegalOperationException ex, WebRequest request) {
        RestResponse errorDetails = new RestResponse(ex.getMessage(), LocalDateTime.now().toString(), HttpStatus.NOT_ACCEPTABLE, 6);
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(InvalidArgumentException.class)
    public final ResponseEntity<RestResponse> handleInvalidArgumentException(InvalidArgumentException ex, WebRequest request) {
        RestResponse errorDetails = new RestResponse(ex.getMessage(), LocalDateTime.now().toString(), HttpStatus.NOT_ACCEPTABLE, 6);
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(MobileNumberInvalidException.class)
    public final ResponseEntity<RestResponse> handleMobileNumberInvalidException(MobileNumberInvalidException ex, WebRequest request) {
        RestResponse errorDetails = new RestResponse(ex.getMessage(), LocalDateTime.now().toString(), HttpStatus.NOT_ACCEPTABLE, 6);
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(java.lang.NullPointerException.class)
    public final ResponseEntity<RestResponse> handleIllegalOperationException(java.lang.NullPointerException ex, WebRequest request) {
        RestResponse errorDetails = new RestResponse(ex.getMessage(), LocalDateTime.now().toString(), HttpStatus.INTERNAL_SERVER_ERROR, 100);
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public final ResponseEntity<RestResponse> handleIllegalOperationException(InsufficientFundsException ex, WebRequest request) {
        RestResponse errorDetails = new RestResponse(ex.getMessage(), LocalDateTime.now().toString(), HttpStatus.INTERNAL_SERVER_ERROR, 16);
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<RestResponse> handleIllegalOperationException(AccessDeniedException ex, WebRequest request) {
        RestResponse errorDetails = new RestResponse(ex.getMessage(), LocalDateTime.now().toString(), HttpStatus.INTERNAL_SERVER_ERROR, 16);
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AccountDoesNotExistException.class)
    public final ResponseEntity<RestResponse> handleIllegalOperationException(AccountDoesNotExistException ex, WebRequest request) {
        RestResponse errorDetails = new RestResponse(ex.getMessage(), LocalDateTime.now().toString(), HttpStatus.INTERNAL_SERVER_ERROR, 14);
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(AccountNotActiveException.class)
    public final ResponseEntity<RestResponse> handleIllegalOperationException(AccountNotActiveException ex, WebRequest request) {
        RestResponse errorDetails = new RestResponse(ex.getMessage(), LocalDateTime.now().toString(), HttpStatus.INTERNAL_SERVER_ERROR, 78);
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BusinessValidationException.class)
    public final ResponseEntity<RestResponse> handleIllegalOperationException(BusinessValidationException ex, WebRequest request) {
        RestResponse errorDetails = new RestResponse(ex.getMessage(), LocalDateTime.now().toString(), HttpStatus.INTERNAL_SERVER_ERROR, 6);
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(AmountInvalidException.class)
    public final ResponseEntity<RestResponse> handleIllegalOperationException(AmountInvalidException ex, WebRequest request) {
        RestResponse errorDetails = new RestResponse(ex.getMessage(), LocalDateTime.now().toString(), HttpStatus.INTERNAL_SERVER_ERROR, 13);
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DeviceNotActiveException.class)
    public final ResponseEntity<RestResponse> handleIllegalOperationException(DeviceNotActiveException ex, WebRequest request) {
        RestResponse errorDetails = new RestResponse(ex.getMessage(), LocalDateTime.now().toString(), HttpStatus.INTERNAL_SERVER_ERROR, 78);
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public final ResponseEntity<RestResponse> handleIllegalOperationException(EntityNotFoundException ex, WebRequest request) {
        RestResponse errorDetails = new RestResponse(ex.getMessage(), LocalDateTime.now().toString(), HttpStatus.INTERNAL_SERVER_ERROR, 6);
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IncorrectPinException.class)
    public final ResponseEntity<RestResponse> handleIllegalOperationException(IncorrectPinException ex, WebRequest request) {
        RestResponse errorDetails = new RestResponse(ex.getMessage(), LocalDateTime.now().toString(), HttpStatus.INTERNAL_SERVER_ERROR, 55);
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TransactionLimitException.class)
    public final ResponseEntity<RestResponse> handleIllegalOperationException(TransactionLimitException ex, WebRequest request) {
        RestResponse errorDetails = new RestResponse(ex.getMessage(), LocalDateTime.now().toString(), HttpStatus.INTERNAL_SERVER_ERROR, 61);
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TransactionNotAllowedException.class)
    public final ResponseEntity<RestResponse> handleIllegalOperationException(TransactionNotAllowedException ex, WebRequest request) {
        RestResponse errorDetails = new RestResponse(ex.getMessage(), LocalDateTime.now().toString(), HttpStatus.INTERNAL_SERVER_ERROR, 57);
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(VelocityException.class)
    public final ResponseEntity<RestResponse> handleIllegalOperationException(VelocityException ex, WebRequest request) {
        RestResponse errorDetails = new RestResponse(ex.getMessage(), LocalDateTime.now().toString(), HttpStatus.INTERNAL_SERVER_ERROR, 61);
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        log.error(ex.getMessage());

        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName() + " " +
                    violation.getPropertyPath() + ": " + violation.getMessage());
        }
        RestResponse errorDetails = new RestResponse(ex.getMessage(), LocalDateTime.now().toString(), HttpStatus.BAD_REQUEST, 6);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleDataIntegrityViolationException(WebRequest request, Exception ex) {
        log.error(ex.getMessage());
        String message = getRootException(ex).getLocalizedMessage();
        if (message.contains("Column")) {
            message = message.replace("Column", "Field");
        }
        if (message.contains("Duplicate")) {
            message = message.replace(message.substring(message.indexOf("for"), message.length()), ". This value must be unique.");
        }
        RestResponse errorDetails = new RestResponse(message, LocalDateTime.now().toString(), HttpStatus.BAD_REQUEST, 6);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);

    }

    private static Throwable getRootException(Throwable exception) {
        Throwable rootException = exception;
        while (rootException.getCause() != null) {
            rootException = rootException.getCause();
        }
        return rootException;
    }

}
