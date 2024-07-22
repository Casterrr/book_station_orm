package cacadores.ifal.poo.book_station.dto.Publisher;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PublisherResponseDTO {
    private String id;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private LocalDateTime creationDate;
}