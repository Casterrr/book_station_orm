package cacadores.ifal.poo.book_station.controller;

import cacadores.ifal.poo.book_station.dto.Loan.LoanRequestDTO;
import cacadores.ifal.poo.book_station.dto.Loan.LoanResponseDTO;
import cacadores.ifal.poo.book_station.service.LoanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoanControllerTest {

    @Mock
    private LoanService loanService;

    @InjectMocks
    private LoanController loanController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateLoan() {
        LoanRequestDTO loanRequestDTO = new LoanRequestDTO();
        LoanResponseDTO loanResponseDTO = new LoanResponseDTO();
        when(loanService.createLoan(loanRequestDTO)).thenReturn(loanResponseDTO);

        ResponseEntity<LoanResponseDTO> response = loanController.createLoan(loanRequestDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(loanResponseDTO, response.getBody());
        verify(loanService).createLoan(loanRequestDTO);
    }

    @Test
    void testGetLoanById() {
        Long id = 1L;
        LoanResponseDTO loanResponseDTO = new LoanResponseDTO();
        when(loanService.getLoanById(id)).thenReturn(loanResponseDTO);

        ResponseEntity<LoanResponseDTO> response = loanController.getLoanById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(loanResponseDTO, response.getBody());
        verify(loanService).getLoanById(id);
    }

    @Test
    void testGetAllLoans() {
        List<LoanResponseDTO> loans = Arrays.asList(new LoanResponseDTO(), new LoanResponseDTO());
        when(loanService.getAllLoans()).thenReturn(loans);

        ResponseEntity<List<LoanResponseDTO>> response = loanController.getAllLoans();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(loans, response.getBody());
        assertEquals(2, response.getBody().size());
        verify(loanService).getAllLoans();
    }

    @Test
    void testUpdateLoan() {
        Long id = 1L;
        LoanRequestDTO loanRequestDTO = new LoanRequestDTO();
        LoanResponseDTO loanResponseDTO = new LoanResponseDTO();
        when(loanService.updateLoan(id, loanRequestDTO)).thenReturn(loanResponseDTO);

        ResponseEntity<LoanResponseDTO> response = loanController.updateLoan(id, loanRequestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(loanResponseDTO, response.getBody());
        verify(loanService).updateLoan(id, loanRequestDTO);
    }

    @Test
    void testDeleteLoan() {
        Long id = 1L;
        doNothing().when(loanService).deleteLoan(id);

        ResponseEntity<Void> response = loanController.deleteLoan(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(loanService).deleteLoan(id);
    }
}