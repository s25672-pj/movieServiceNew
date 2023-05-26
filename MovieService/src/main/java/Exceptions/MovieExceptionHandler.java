package Exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MovieExceptionHandler {
    @ExceptionHandler(value = {MovieNotFoundException.class})
    public ResponseEntity<Void> notFound(){
        return ResponseEntity.notFound().build();
    }

}
