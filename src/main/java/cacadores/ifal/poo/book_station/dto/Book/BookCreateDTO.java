package cacadores.ifal.poo.book_station.dto.Book;

import lombok.Data;

@Data
public class BookCreateDTO {
    private String title;
    private String isbn;
    private String synopsis;
    private String language;
    private Integer year;
    private Integer availableQuantity;
    private Integer pages;
    private String status;
    private String publisherId;
    private String authorId;
    private String genreId;
}