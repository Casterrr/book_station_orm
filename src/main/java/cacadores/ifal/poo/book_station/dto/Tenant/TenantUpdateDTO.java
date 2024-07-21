package cacadores.ifal.poo.book_station.dto.Tenant;

import lombok.Data;

@Data
public class TenantUpdateDTO {
    private String name;
    private String email;
    private String address;
    private String phone;
}