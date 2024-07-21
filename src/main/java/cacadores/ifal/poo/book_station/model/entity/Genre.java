package cacadores.ifal.poo.book_station.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_GENRE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", nullable = false)
    private String id;

    @Column(name = "NAME", nullable = false, length = 20)
    private String name;

    @Column(name = "CREATION_DATE", nullable = false)
    private LocalDateTime creationDate;
}