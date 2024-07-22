package cacadores.ifal.poo.book_station.dto.Loan;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LoanRequestDTO {
    private String itemId;
    private String userId;
    private LocalDate expectedReturnDate;
}