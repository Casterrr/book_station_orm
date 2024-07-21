package cacadores.ifal.poo.book_station.model.entity.items;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_book")
@Getter
@Setter
@AllArgsConstructor
public class Book extends Item {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "isbn", nullable = false, length = 20)
    private String isbn;

    @Column(name = "synopsis", length = 4000)
    private String synopsis;

    @Column(name = "language", nullable = false, length = 50)
    private String language;

    public Book() {
        this.type = "BOOK";
        this.registrationDate = LocalDateTime.now();
    }
}