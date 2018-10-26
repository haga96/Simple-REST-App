package warsztaty.spring.ailleron.exceptions.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import warsztaty.spring.ailleron.exceptions.UserExistException;
import warsztaty.spring.ailleron.exceptions.UserNotFoundException;

import javax.jws.WebResult;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class CustomizeResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity handleUserNotFoundException(UserNotFoundException ex, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserExistException.class)
    public final ResponseEntity handleUserExistException(UserExistException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.CONFLICT);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,HttpStatus status, WebRequest request){
        List<String> listOfErrors = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach(e -> listOfErrors.add(listOfErrors.size() +1 + ": " + e.getDefaultMessage()));
        ExceptionResponse response = new ExceptionResponse(new Date(), "Validation Failed", listOfErrors.toString());
        return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
    }
}
