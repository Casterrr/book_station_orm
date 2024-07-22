package cacadores.ifal.poo.book_station.dto.Genre;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class GenreResponseDTO {
  private String id;
  private String name;
  private String description;
  private LocalDateTime creationDate;
}