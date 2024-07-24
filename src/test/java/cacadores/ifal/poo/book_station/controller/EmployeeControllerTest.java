package cacadores.ifal.poo.book_station.controller;

import cacadores.ifal.poo.book_station.dto.Employee.EmployeeCreateDTO;
import cacadores.ifal.poo.book_station.dto.Employee.EmployeeResponseDTO;
import cacadores.ifal.poo.book_station.dto.Employee.EmployeeUpdateDTO;
import cacadores.ifal.poo.book_station.service.EmployeeService;
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

class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllEmployees() {
        List<EmployeeResponseDTO> employees = Arrays.asList(new EmployeeResponseDTO(), new EmployeeResponseDTO());
        when(employeeService.getAllEmployees()).thenReturn(employees);

        ResponseEntity<List<EmployeeResponseDTO>> response = employeeController.getAllEmployees();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employees, response.getBody());
        assertEquals(2, response.getBody().size());
        verify(employeeService).getAllEmployees();
    }

    @Test
    void testGetEmployeeById() {
        String id = "1";
        EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO();
        when(employeeService.getEmployeeById(id)).thenReturn(employeeResponseDTO);

        ResponseEntity<EmployeeResponseDTO> response = employeeController.getEmployeeById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeeResponseDTO, response.getBody());
        verify(employeeService).getEmployeeById(id);
    }

    @Test
    void testCreateEmployee() {
        EmployeeCreateDTO employeeCreateDTO = new EmployeeCreateDTO();
        EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO();
        when(employeeService.createEmployee(employeeCreateDTO)).thenReturn(employeeResponseDTO);

        ResponseEntity<EmployeeResponseDTO> response = employeeController.createEmployee(employeeCreateDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(employeeResponseDTO, response.getBody());
        verify(employeeService).createEmployee(employeeCreateDTO);
    }

    @Test
    void testUpdateEmployee() {
        String id = "1";
        EmployeeUpdateDTO employeeUpdateDTO = new EmployeeUpdateDTO();
        EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO();
        when(employeeService.updateEmployee(id, employeeUpdateDTO)).thenReturn(employeeResponseDTO);

        ResponseEntity<EmployeeResponseDTO> response = employeeController.updateEmployee(id, employeeUpdateDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeeResponseDTO, response.getBody());
        verify(employeeService).updateEmployee(id, employeeUpdateDTO);
    }

    @Test
    void testDeleteEmployee() {
        String id = "1";
        doNothing().when(employeeService).deleteEmployee(id);

        ResponseEntity<Void> response = employeeController.deleteEmployee(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(employeeService).deleteEmployee(id);
    }
}