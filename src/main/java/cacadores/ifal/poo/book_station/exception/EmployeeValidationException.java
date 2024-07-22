package cacadores.ifal.poo.book_station.exception;

public class EmployeeValidationException extends IllegalArgumentException {
    public EmployeeValidationException(String message) {
        super(message);
    }
}