package cacadores.ifal.poo.book_station.model.entity;

import cacadores.ifal.poo.book_station.model.entity.items.Item;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_RESERVA")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RESERVA", nullable = false)
    private Integer idReserva;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "ID_ITEM")
    private Item item;

    @Column(name = "IND_ATENDIDA", nullable = false, length = 1)
    private String indicadorAtendida;

    @Column(name = "DTH_RESERVA", nullable = false)
    private LocalDateTime dataReserva;
}