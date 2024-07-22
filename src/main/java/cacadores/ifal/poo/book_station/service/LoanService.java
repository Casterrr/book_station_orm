package cacadores.ifal.poo.book_station.service;

import cacadores.ifal.poo.book_station.dto.Book.BookResponseDTO;
import cacadores.ifal.poo.book_station.dto.Book.BookUpdateDTO;
import cacadores.ifal.poo.book_station.dto.Loan.LoanRequestDTO;
import cacadores.ifal.poo.book_station.dto.Loan.LoanResponseDTO;
import cacadores.ifal.poo.book_station.exception.LoanNotFoundException;
import cacadores.ifal.poo.book_station.model.entity.Loan;
import cacadores.ifal.poo.book_station.model.entity.items.Book;
import cacadores.ifal.poo.book_station.repository.LoanRepository;

import org.hibernate.loader.internal.LoadAccessContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanService {

    private final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public LoanResponseDTO createLoan(LoanRequestDTO loanRequestDTO) {
        Loan loan = new Loan();
        BeanUtils.copyProperties(loanRequestDTO, loan);
        loan = loanRepository.save(loan);

        return convertToLoanResponseDTO(loan);
    }

    private LoanResponseDTO convertToLoanResponseDTO(Loan loan) {
        LoanResponseDTO dto = new LoanResponseDTO();
        BeanUtils.copyProperties(loan, dto);
        dto.setUserId(loan.getUser().getId());
        dto.setItemId(loan.getItem().getId());
        dto.setLoanDate(loan.getLoanDate());
        dto.setReturnDate(loan.getExpectedReturnDate());
        return dto;
    }

    public LoanResponseDTO getLoanById(Long id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException("Loan not found with id: " + id));
        return convertToLoanResponseDTO(loan);
    }

    public List<LoanResponseDTO> getAllLoans() {
        List<Loan> loans = loanRepository.findAll();
        return loans.stream().map(this::convertToLoanResponseDTO).collect(Collectors.toList());
    }

    public LoanResponseDTO updateLoan(Long id, LoanRequestDTO loanRequestDTO) {
        Loan existingLoan = loanRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException("Loan not found with id: " + id));
        BeanUtils.copyProperties(loanRequestDTO, existingLoan);

        existingLoan = loanRepository.save(existingLoan);
        return convertToLoanResponseDTO(existingLoan);
    }

    public void deleteLoan(Long id) {
        if (!loanRepository.existsById(id)) {
            throw new LoanNotFoundException("Loan not found with id: " + id);
        }
        loanRepository.deleteById(id);
    }
}