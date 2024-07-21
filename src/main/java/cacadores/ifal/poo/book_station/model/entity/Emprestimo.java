package cacadores.ifal.poo.book_station.model.entity;

import cacadores.ifal.poo.book_station.model.entity.items.Item;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_EMPRESTIMO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EMPRESTIMO", nullable = false)
    private Integer idEmprestimo;

    @ManyToOne
    @JoinColumn(name = "ID_ITEM")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usuario;

    @Column(name = "DTH_EMPRESTIMO", nullable = false)
    private LocalDateTime dataEmprestimo;

    @Column(name = "DTH_DEVOLUCAO_PREVISTA", nullable = false)
    private LocalDate dataDevolucaoPrevista;

    @Column(name = "DAT_DEVOLUCAO_REAL")
    private LocalDate dataDevolucaoReal;

    @Column(name = "VALOR_MULTA", precision = 10, scale = 2)
    private BigDecimal valorMulta;

    @Column(name = "DTH_MULTA")
    private LocalDateTime dataMulta;
}