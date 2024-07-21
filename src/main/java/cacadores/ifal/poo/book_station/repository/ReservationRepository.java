package cacadores.ifal.poo.book_station.repository;

import cacadores.ifal.poo.book_station.model.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String> {
}