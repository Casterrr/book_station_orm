package cacadores.ifal.poo.book_station.model.entity.items;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_maganize")
@Getter
@Setter
public class Magazine extends Item {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
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