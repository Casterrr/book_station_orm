package cacadores.ifal.poo.book_station.dto.Tenant;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TenantResponseDTO {
    private String id;
    private String name;
    private String email;
    private String address;
    private String cpf;
    private String phone;
    private LocalDateTime registrationTime;
}