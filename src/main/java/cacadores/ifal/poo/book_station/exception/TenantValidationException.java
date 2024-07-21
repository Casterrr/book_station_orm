package cacadores.ifal.poo.book_station.exception;

public class TenantValidationException extends RuntimeException {
    public TenantValidationException(String message) {
        super(message);
    }
}