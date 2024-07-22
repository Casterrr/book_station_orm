package cacadores.ifal.poo.book_station.exception.genre;

public class GenreNotFoundException extends RuntimeException {
    public GenreNotFoundException(String message) {
        super(message);
    }
}