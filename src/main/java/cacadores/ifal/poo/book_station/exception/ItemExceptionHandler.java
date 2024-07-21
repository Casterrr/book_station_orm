package cacadores.ifal.poo.book_station.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ItemExceptionHandler {
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<String> handleBookNotFoundException(BookNotFoundException ex) {
        return new ResponseEntity<>("Book does not exist!", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MagazineNotFoundException.class)
    public ResponseEntity<String> handleMagazineNotFoundException(MagazineNotFoundException ex) {
        return new ResponseEntity<>("Magazine does not exist!", HttpStatus.BAD_REQUEST);
    }
}
