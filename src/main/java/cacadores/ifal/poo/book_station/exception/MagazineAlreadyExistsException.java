package cacadores.ifal.poo.book_station.exception;

public class MagazineAlreadyExistsException extends RuntimeException {
    public MagazineAlreadyExistsException(String message) {
        super(message);
    }
}