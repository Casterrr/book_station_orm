package cacadores.ifal.poo.book_station.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BookNotFoundException.class)
  public ResponseEntity<String> handleBookNotFoundException(BookNotFoundException ex) {
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(BookAlreadyExistsException.class)
  public ResponseEntity<String> handleBookAlreadyExistsException(BookAlreadyExistsException ex) {
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
  }

  // Tratamento genérico para outras exceções não tratadas
  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleGenericException(Exception ex) {
      return new ResponseEntity<>("Oooopa!! Ocorreu um erro interno no servidor...", HttpStatus.INTERNAL_SERVER_ERROR);
  }
}