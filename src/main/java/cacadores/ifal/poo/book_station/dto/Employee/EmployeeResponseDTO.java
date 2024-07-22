package cacadores.ifal.poo.book_station.dto.Employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class EmployeeResponseDTO {
    private String id;
    private String name;
    private String email;
    private String position;
    private BigDecimal salary;
    private String workCardNumber;  // Campo adicionado
    private LocalDate hireDate;
    private LocalDateTime registrationTime;
}