package cacadores.ifal.poo.book_station.model.entity;

import cacadores.ifal.poo.book_station.model.entity.items.Item;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "TB_RESERVATION")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RESERVATION", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_USER")
    private User user;

    @ManyToOne
    @JoinColumn(name = "ID_ITEM")
    private Item item;

    @Column(name = "IS_FULFILLED", nullable = false)
    private Boolean isFulfilled;

    @Column(name = "RESERVATION_DATE", nullable = false)
    @CreationTimestamp
    private LocalDateTime reservationDate;
}