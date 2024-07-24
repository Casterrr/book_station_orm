package cacadores.ifal.poo.book_station.dto.Reservation;

import cacadores.ifal.poo.book_station.model.entity.User;
import cacadores.ifal.poo.book_station.model.entity.items.Item;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Data
public class ReservationRequestDTO {
    private Long user;
    private Long item;
    private Boolean isFullFilled;
    private LocalDate reservationDate;
}
