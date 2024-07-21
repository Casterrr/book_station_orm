package cacadores.ifal.poo.book_station.dto.User;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserResponseDTO {
    private String id;
    private String name;
    private String email;
    private LocalDateTime registrationTime;
}