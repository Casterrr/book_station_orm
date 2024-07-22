package cacadores.ifal.poo.book_station.service;

import cacadores.ifal.poo.book_station.model.entity.Reservation;
import cacadores.ifal.poo.book_station.repository.ReservationRepository;
import cacadores.ifal.poo.book_station.exception.ReservationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException("Reservation not found with id: " + id));
    }

    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Reservation updateReservation(Long id, Reservation reservationDetails) {
        Reservation reservation = getReservationById(id);
        reservation.setUser(reservationDetails.getUser());
        reservation.setItem(reservationDetails.getItem());
        reservation.setIsFulfilled(reservationDetails.getIsFulfilled());
        reservation.setReservationDate(reservationDetails.getReservationDate());
        return reservationRepository.save(reservation);
    }

    public void deleteReservation(Long id) {
        Reservation reservation = getReservationById(id);
        reservationRepository.delete(reservation);
    }
}