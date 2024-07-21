package cacadores.ifal.poo.book_station.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_EDITORA")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EDITORA", nullable = false)
    private Integer idEditora;

    @Column(name = "NOME_EDITORA", nullable = false, length = 100)
    private String nomeEditora;

    @Column(name = "ENDERECO_EDITORA", nullable = false, length = 255)
    private String enderecoEditora;

    @Column(name = "TELEFONE_EDITORA", nullable = false, length = 255)
    private String telefoneEditora;

    @Column(name = "DTH_CADASTRO_EDITORA", nullable = false)
    private LocalDateTime dataCadastroEditora;

    // Os getters e setters são gerados automaticamente pelo Lombok
}