package cacadores.ifal.poo.book_station.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "TB_FUNCIONARIO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_FUNCIONARIO", nullable = false)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "ID_USER")
    private User user;

    @Column(name = "DSC_CARGO", nullable = false, length = 50)
    private String cargo;

    @Column(name = "VLR_SALARIO", nullable = false, precision = 10, scale = 2)
    private BigDecimal salario;

    @Column(name = "NUM_CTPS", nullable = false, length = 11)
    private String numeroCTPS;

    @Column(name = "DAT_ADMISSAO", nullable = false)
    private LocalDate dataAdmissao;
}