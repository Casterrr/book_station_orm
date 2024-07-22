package cacadores.ifal.poo.book_station.dto.Book;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BookResponseDTO {
    private String id;
    private String title;
    private String isbn;
    private String synopsis;
    private String language;
    private Integer year;
    private Integer availableQuantity;
    private Integer pages;
    private String status;
    private String publisherName;
    private String authorName;
    private String genreName;
    private LocalDateTime registrationDate;
}