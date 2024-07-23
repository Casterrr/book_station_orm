package cacadores.ifal.poo.book_station.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException (String message) {
        super(message);
    }
}
