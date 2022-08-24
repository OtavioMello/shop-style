package br.com.project.shopstyle.mscatalog.exception.handler;

import br.com.project.shopstyle.mscatalog.exception.BusinessException;
import br.com.project.shopstyle.mscatalog.exception.StandardError;
import br.com.project.shopstyle.mscatalog.exception.ValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<StandardError> businessExceptionHandler(BusinessException exception, HttpServletRequest request){

        StandardError standardError = new StandardError(
                exception.getMessage(),
                exception.getStatus(),
                request.getRequestURI(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(exception.getStatus()).body(standardError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationError>> methodArgumentNotValidException(MethodArgumentNotValidException exception){

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ArrayList<>(
                fieldErrors.stream().map(f -> new ValidationError(f.getField(), f.getDefaultMessage().toUpperCase())).toList()));
    }
}
