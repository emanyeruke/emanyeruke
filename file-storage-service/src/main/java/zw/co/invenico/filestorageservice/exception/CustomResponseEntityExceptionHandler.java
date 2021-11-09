package zw.co.invenico.filestorageservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import zw.co.invenico.filestorageservice.dto.RestResponse;

@Slf4j
@ControllerAdvice
@RestController

public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


//    @ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)
//    public final ResponseEntity<ErrorMessage> handleAccessDeniedException(org.springframework.security.access.AccessDeniedException ex, WebRequest request) {
//        ErrorMessage errorDetails = new ErrorMessage(new Date(), ex.getMessage(), request.getDescription(false), ex.getClass().getName());
//        ex.printStackTrace();
//        return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
//    }


    @ExceptionHandler(FileNotFoundException.class)
    public final ResponseEntity<RestResponse> handleRecordNotFoundException(FileNotFoundException ex, WebRequest request) {
        RestResponse errorDetails = new RestResponse(ex.getMessage(), HttpStatus.NOT_FOUND, "Failed");
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FileStorageException.class)
    public final ResponseEntity<RestResponse> handleIllegalOperationException(FileStorageException ex, WebRequest request) {
        RestResponse errorDetails = new RestResponse(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE, "Failed");
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_ACCEPTABLE);
    }

}
