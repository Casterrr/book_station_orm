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

  @ExceptionHandler(MagazineNotFoundException.class)
    public ResponseEntity<String> handleMagazineNotFoundException(MagazineNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

  @ExceptionHandler(MagazineAlreadyExistsException.class)
  public ResponseEntity<String> handleMagazineAlreadyExistsException(MagazineAlreadyExistsException ex) {
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
  }

  @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        String errorMessage = "Intern server error: " + ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}