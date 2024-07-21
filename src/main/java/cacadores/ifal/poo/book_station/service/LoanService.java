package cacadores.ifal.poo.book_station.service;

import cacadores.ifal.poo.book_station.exception.LoanNotFoundException;
import cacadores.ifal.poo.book_station.model.entity.Loan;
import cacadores.ifal.poo.book_station.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

    private final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public Loan createLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    public Loan getLoanById(String id) {
        return loanRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException("Loan not found with id: " + id));
    }

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Loan updateLoan(Loan loan) {
        if (!loanRepository.existsById(loan.getId())) {
            throw new LoanNotFoundException("Loan not found with id: " + loan.getId());
        }
        return loanRepository.save(loan);
    }

    public void deleteLoan(String id) {
        if (!loanRepository.existsById(id)) {
            throw new LoanNotFoundException("Loan not found with id: " + id);
        }
        loanRepository.deleteById(id);
    }
}