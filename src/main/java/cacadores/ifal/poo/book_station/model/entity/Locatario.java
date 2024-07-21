package cacadores.ifal.poo.book_station.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_LOCATARIO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Locatario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LOCATARIO", nullable = false)
    private Integer idLocatario;

    @OneToOne
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usuario;

    @Column(name = "ENDERECO_LOCATARIO", nullable = false, length = 255)
    private String enderecoLocatario;

    @Column(name = "CPF_LOCATARIO", nullable = false, length = 11)
    private String cpfLocatario;

    @Column(name = "TELEFONE_LOCATARIO", nullable = false, length = 20)
    private String telefoneLocatario;
}