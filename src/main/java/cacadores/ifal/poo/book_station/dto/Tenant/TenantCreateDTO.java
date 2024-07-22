package cacadores.ifal.poo.book_station.dto.Tenant;

import lombok.Data;

@Data
public class TenantCreateDTO {
    private String name;
    private String email;
    private String password;
    private String address;
    private String cpf;
    private String phone;
}