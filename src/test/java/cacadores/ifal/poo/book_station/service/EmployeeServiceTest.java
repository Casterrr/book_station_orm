package cacadores.ifal.poo.book_station.service;

import cacadores.ifal.poo.book_station.dto.Employee.EmployeeCreateDTO;
import cacadores.ifal.poo.book_station.exception.EmployeeValidationException;
import cacadores.ifal.poo.book_station.model.entity.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import cacadores.ifal.poo.book_station.repository.EmployeeRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateEmployee_WithValidData_ShouldNotThrowException() {
        // Arrange
        EmployeeCreateDTO dto = new EmployeeCreateDTO();
        dto.setName("John Doe");
        dto.setEmail("john@example.com");
        dto.setPassword("password123");
        dto.setPosition("Manager");
        dto.setSalary(new BigDecimal("5000.00"));
        dto.setWorkCardNumber("123456");

        // Mock behavior
        when(passwordEncoder.encode(any())).thenReturn("encodedPassword");
        when(employeeRepository.save(any())).thenAnswer(invocation -> {
            Employee savedEmployee = invocation.getArgument(0);
            savedEmployee.setId(null); // Set an ID to simulate database save
            return savedEmployee;
        });

        // Act & Assert
        assertDoesNotThrow(() -> employeeService.createEmployee(dto));
    }

    @Test
    void testCreateEmployee_WithInvalidData_ShouldThrowException() {
        // Arrange
        EmployeeCreateDTO dto = new EmployeeCreateDTO();
        // Not setting any fields to trigger validation exception

        // Act & Assert
        assertThrows(EmployeeValidationException.class, () -> employeeService.createEmployee(dto));
    }
}