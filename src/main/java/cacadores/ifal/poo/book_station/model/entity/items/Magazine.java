package cacadores.ifal.poo.book_station.model.entity.items;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_maganize")
@Getter
@Setter
public class Magazine extends Item {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "maganize_id")
    private String id;

    @Column(name = "issn")
    private String issn;

    @Column(name = "edition")
    private Integer edition;

    @Column(name = "volume")
    private Integer volume;

    @Column(name = "description")
    private String description;

    public Magazine() {
        this.type = "MAGAZINE";
        this.registrationDate = LocalDateTime.now();
    }
}