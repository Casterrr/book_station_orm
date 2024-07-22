package cacadores.ifal.poo.book_station.dto.Author;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AuthorUpdateDTO {
    private String name;
    private String nationality;
    private String biography;
    private LocalDate birthDate;  // Adicionado campo de data de nascimento
}