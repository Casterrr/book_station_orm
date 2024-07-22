package cacadores.ifal.poo.book_station.dto.Magazine;

import lombok.Data;

@Data
public class MagazineCreateDTO {
    private String title;
    private String issn;
    private Integer edition;
    private Integer year;
    private Integer availableQuantity;
    private Integer pages;
    private String status;
    private Long publisherId;
    private Long genreId;
}