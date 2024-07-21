package cacadores.ifal.poo.book_station.exception;

public class GenreNotFoundException extends RuntimeException {
    public GenreNotFoundException(String message) {
        super(message);
    }
}