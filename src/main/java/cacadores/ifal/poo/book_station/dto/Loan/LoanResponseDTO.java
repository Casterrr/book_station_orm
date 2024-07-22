package cacadores.ifal.poo.book_station.dto.Loan;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class LoanResponseDTO {
    private Long id;
    private String itemId;
    private String userId;
    private LocalDateTime loanDate;
    private LocalDate returnDate;
    private BigDecimal fineAmount;
    private LocalDateTime fineDate;
}