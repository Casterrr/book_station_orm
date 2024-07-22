package cacadores.ifal.poo.book_station.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_EMPLOYEE")
@Getter
@Setter
@NoArgsConstructor
public class Employee extends User {

    @Column(name = "POSITION", nullable = false, length = 50)
    private String position;

    @Column(name = "SALARY", nullable = false, precision = 10, scale = 2)
    private BigDecimal salary;

    @Column(name = "WORK_CARD_NUMBER", nullable = false, length = 11)
    private String workCardNumber;

    @Column(name = "HIRE_DATE", nullable = false)
    private LocalDate hireDate;

    // Constructor
    public Employee(String id, String name, String email, String password, LocalDateTime createdAt,
            LocalDateTime updatedAt,
            String position, BigDecimal salary, String workCardNumber, LocalDate hireDate) {
        super(id, name, email, password, createdAt, updatedAt);
        this.position = position;
        this.salary = salary;
        this.workCardNumber = workCardNumber;
        this.hireDate = hireDate;
    }

    @PrePersist
    protected void onCreate() {
        if (hireDate == null) {
            hireDate = LocalDate.now();
        }
    }
}