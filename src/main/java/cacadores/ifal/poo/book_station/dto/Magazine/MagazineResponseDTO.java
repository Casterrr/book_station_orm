package cacadores.ifal.poo.book_station.dto.Magazine;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MagazineResponseDTO {
    private String id;
    private String title;
    private String issn;
    private Integer edition;
    private Integer year;
    private Integer availableQuantity;
    private Integer pages;
    private String status;
    private String publisherName;
    private String genreName;
    private LocalDateTime registrationDate;
}