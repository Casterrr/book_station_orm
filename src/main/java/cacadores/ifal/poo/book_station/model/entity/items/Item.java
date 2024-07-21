package cacadores.ifal.poo.book_station.model.entity.items;

import cacadores.ifal.poo.book_station.model.entity.Author;
import cacadores.ifal.poo.book_station.model.entity.Genero;
import cacadores.ifal.poo.book_station.model.entity.Publisher;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Table(name = "tb_item")
@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    // @ManyToOne
    // @JoinColumn(name = "publisher_id")
    // private Publisher publisher;

    // @ManyToOne
    // @JoinColumn(name = "author_id")
    // private Author author;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genero genre;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "year")
    private Integer year;

    @Column(name = "type", nullable = false)
    protected String type;

    @Column(name = "available_quantity", nullable = false)
    private Integer availableQuantity;

    @Column(name = "pages", nullable = false)
    private Integer pages;

    @Column(name = "status")
    private String status;

    @Column(name = "registration_date")
    protected LocalDateTime registrationDate;
}