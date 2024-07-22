package cacadores.ifal.poo.book_station.dto.Employee;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class EmployeeUpdateDTO {
    private String name;
    private String email;
    private String position;
    private BigDecimal salary;
    private String workCardNumber;  // Campo adicionado
    private LocalDate hireDate;  // Opcional
}