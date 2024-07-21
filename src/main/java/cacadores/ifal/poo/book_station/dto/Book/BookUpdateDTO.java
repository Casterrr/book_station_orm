package cacadores.ifal.poo.book_station.dto.Book;

import lombok.Data;

@Data
public class BookUpdateDTO {
    private String title;
    private int year;
    private int availableQuantity;
    private int pages;
    private String isbn;
    private String synopsis;
    private String language;
    private String publisherId;
    private String authorId;
    private String genreId;
}