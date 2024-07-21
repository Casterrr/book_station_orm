package cacadores.ifal.poo.book_station.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_PUBLISHER")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", nullable = false)
    private String id;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "ADDRESS", nullable = false, length = 255)
    private String address;

    @Column(name = "PHONE", nullable = false, length = 255)
    private String phone;

    @Column(name = "REGISTRATION_DATE", nullable = false)
    private LocalDateTime registrationDate;
}