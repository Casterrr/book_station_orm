package cacadores.ifal.poo.book_station.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_AUTOR")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_AUTOR", nullable = false)
    private Integer idAutor;

    @Column(name = "NOME_AUTOR", nullable = false, length = 255)
    private String nomeAutor;

    @Column(name = "DAT_NASCIMENTO", nullable = false)
    private LocalDate dataNascimentoAutor;

    @Column(name = "BIOGRAFIA", length = 255)
    private String biografiaAutor;

    @Column(name = "DTH_CADASTRO_AUTOR", nullable = false)
    private LocalDateTime dataCadastroAutor;

    // Os getters e setters são gerados automaticamente pelo Lombok
}