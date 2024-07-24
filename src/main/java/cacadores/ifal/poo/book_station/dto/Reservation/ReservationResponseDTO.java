package cacadores.ifal.poo.book_station.dto.Reservation;

import cacadores.ifal.poo.book_station.model.entity.User;
import cacadores.ifal.poo.book_station.model.entity.items.Item;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ReservationResponseDTO {
    private Long id;
    private Long user;
    private Long item;
    private boolean isFullFilled;
    private LocalDateTime reservationDate;
}
