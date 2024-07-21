package cacadores.ifal.poo.book_station.model.entity.items;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_maganize")
@Getter
@Setter
public class Magazine extends Item {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "maganize_id", nullable = false)
    private String id;

    @Column(name = "issn", nullable = false, length = 20)
    private String issn;

    @Column(name = "edition", nullable = false)
    private Integer edition;

    @Column(name = "volume", nullable = false)
    private Integer volume;

    @Column(name = "description", length = 200)
    private String description;

    public Magazine() {
        this.type = "MAGAZINE";
    }
}