package cacadores.ifal.poo.book_station.dto.Book;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BookResponseDTO {
    private String id;
    private String title;
    private int year;
    private int availableQuantity;
    private int pages;
    private String isbn;
    private String synopsis;
    private String language;
    private String publisherName;
    private String authorName;
    private String genreName;
    private LocalDateTime registrationDate;
}