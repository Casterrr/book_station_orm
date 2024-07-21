package cacadores.ifal.poo.book_station.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_TENANT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tenant extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private String id;

    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;

    @Column(name = "ADDRESS", nullable = false, length = 255)
    private String address;

    @Column(name = "CPF", nullable = false, length = 11)
    private String cpf;

    @Column(name = "PHONE", nullable = false, length = 20)
    private String phone;
}