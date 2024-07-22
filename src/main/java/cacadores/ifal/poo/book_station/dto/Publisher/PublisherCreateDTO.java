package cacadores.ifal.poo.book_station.dto.Publisher;

import lombok.Data;

@Data
public class PublisherCreateDTO {
    private String name;
    private String address;
    private String phone;
    private String email;
}