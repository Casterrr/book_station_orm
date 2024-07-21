package cacadores.ifal.poo.book_station.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_GENERO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_GENERO", nullable = false)
    private Integer idGenero;

    @Column(name = "NOME_GENERO", nullable = false, length = 20)
    private String nomeGenero;

    @Column(name = "DTH_CADASTRO_GENERO", nullable = false)
    private LocalDateTime dataCadastroGenero;

    // Os getters e setters são gerados automaticamente pelo Lombok
}