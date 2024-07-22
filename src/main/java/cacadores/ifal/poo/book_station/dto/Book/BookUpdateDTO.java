package cacadores.ifal.poo.book_station.dto.Book;

import lombok.Data;

@Data
public class BookUpdateDTO {
    private String title;
    private String isbn;
    private String synopsis;
    private String language;
    private Integer year;
    private Integer availableQuantity;
    private Integer pages;
    private String status;
    private Long publisherId;
    private Long authorId;
    private Long genreId;
}