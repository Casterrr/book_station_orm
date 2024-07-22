package cacadores.ifal.poo.book_station.dto.Publisher;

import lombok.Data;

@Data
public class PublisherUpdateDTO {
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
}