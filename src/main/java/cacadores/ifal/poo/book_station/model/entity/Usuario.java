package cacadores.ifal.poo.book_station.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_USUARIO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO", nullable = false)
    private Integer idUsuario;

    @Column(name = "NOME_USUARIO", nullable = false, length = 100)
    private String nomeUsuario;

    @Column(name = "EMAIL", nullable = false, length = 40)
    private String emailUsuario;

    @Column(name = "SENHA_USUARIO", nullable = false, length = 255)
    private String senhaUsuario;

    @Column(name = "DTH_CADASTRO_USUARIO", nullable = false)
    private LocalDateTime dataCadastroUsuario;
}