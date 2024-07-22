package cacadores.ifal.poo.book_station.dto.User;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserUpdateDTO {
    private String name;
    private String email;
    private LocalDateTime updatedAt;

}