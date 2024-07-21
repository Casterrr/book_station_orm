package cacadores.ifal.poo.book_station.dto;

import lombok.Data;

@Data
public class UserCreateDTO {
    private String name;
    private String email;
    private String password;
}