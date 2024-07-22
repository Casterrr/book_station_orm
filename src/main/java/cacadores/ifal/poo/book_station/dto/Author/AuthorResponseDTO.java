package cacadores.ifal.poo.book_station.dto.Author;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AuthorResponseDTO {
    private String id;
    private String name;
    private String nationality;
    private String biography;
    private LocalDate birthDate;  // Adicionado campo de data de nascimento
    private LocalDateTime creationDate;
}