package cacadores.ifal.poo.book_station.controller;

import cacadores.ifal.poo.book_station.model.entity.Reservation;
import cacadores.ifal.poo.book_station.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ReservationControllerTest {

    @Mock
    private ReservationService reservationService;

    @InjectMocks
    private ReservationController reservationController;

    private Reservation reservation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        reservation = new Reservation(); // Initialize with necessary data
    }

    @Test
    void getAllReservations() {
        List<Reservation> reservations = Arrays.asList(reservation);
        when(reservationService.getAllReservations()).thenReturn(reservations);

        List<Reservation> result = reservationController.getAllReservations();

        assertEquals(reservations, result);
        verify(reservationService).getAllReservations();
    }

    @Test
    void getReservationById() {
        Long id = 1L;
        when(reservationService.getReservationById(id)).thenReturn(reservation);

        ResponseEntity<Reservation> response = reservationController.getReservationById(id);

        assertEquals(ResponseEntity.ok(reservation), response);
        verify(reservationService).getReservationById(id);
    }

    // Add more tests for createReservation, updateReservation, and
    // deleteReservation
}