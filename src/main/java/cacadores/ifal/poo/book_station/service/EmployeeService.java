package cacadores.ifal.poo.book_station.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import cacadores.ifal.poo.book_station.exception.WorkCardNumberException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cacadores.ifal.poo.book_station.dto.Employee.EmployeeCreateDTO;
import cacadores.ifal.poo.book_station.dto.Employee.EmployeeResponseDTO;
import cacadores.ifal.poo.book_station.dto.Employee.EmployeeUpdateDTO;
import cacadores.ifal.poo.book_station.exception.EmployeeAlreadyExistsException;
import cacadores.ifal.poo.book_station.exception.EmployeeNotFoundException;
import cacadores.ifal.poo.book_station.exception.EmployeeValidationException;
import cacadores.ifal.poo.book_station.model.entity.Employee;
import cacadores.ifal.poo.book_station.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<EmployeeResponseDTO> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(this::convertToEmployeeResponseDTO)
                .collect(Collectors.toList());
    }

    public EmployeeResponseDTO getEmployeeById(String id) {
        return employeeRepository.findById(id)
                .map(this::convertToEmployeeResponseDTO)
                .orElseThrow(() -> new EmployeeNotFoundException("Funcionário com ID " + id + " não encontrado"));
    }

    public EmployeeResponseDTO createEmployee(EmployeeCreateDTO employeeCreateDTO) {
        validateEmployee(employeeCreateDTO);
        
        if (employeeRepository.existsByEmail(employeeCreateDTO.getEmail())) {
            throw new EmployeeAlreadyExistsException("Funcionário com o email " + employeeCreateDTO.getEmail() + " já existe");
        }

        if (employeeRepository.existsByWorkCardNumber(employeeCreateDTO.getWorkCardNumber())){
            throw new WorkCardNumberException("Número de empregado " + employeeCreateDTO.getWorkCardNumber() + " já cadastrado");
        }
        employeeCreateDTO.setPassword(passwordEncoder.encode(employeeCreateDTO.getPassword()));
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeCreateDTO, employee);
        
        if (employee.getHireDate() == null) {
            employee.setHireDate(LocalDate.now());
        }
        
        employee = employeeRepository.save(employee);
        return convertToEmployeeResponseDTO(employee);
    }

    public EmployeeResponseDTO updateEmployee(String id, EmployeeUpdateDTO employeeUpdateDTO) {
        Employee existingEmployee = findEmployeeById(id);
        validateEmployeeUpdate(employeeUpdateDTO);
        
        BeanUtils.copyProperties(employeeUpdateDTO, existingEmployee);
        existingEmployee = employeeRepository.save(existingEmployee);
        return convertToEmployeeResponseDTO(existingEmployee);
    }

    private void validateEmployee(EmployeeCreateDTO employeeCreateDTO) {
        if (employeeCreateDTO.getName() == null || employeeCreateDTO.getName().trim().isEmpty()) {
            throw new EmployeeValidationException("O nome do funcionário é obrigatório");
        }
        if (employeeCreateDTO.getEmail() == null || employeeCreateDTO.getEmail().trim().isEmpty()) {
            throw new EmployeeValidationException("O email do funcionário é obrigatório");
        }
        if (employeeCreateDTO.getPassword() == null || employeeCreateDTO.getPassword().trim().isEmpty()) {
            throw new EmployeeValidationException("A senha do funcionário é obrigatória");
        }
        if (employeeCreateDTO.getPosition() == null || employeeCreateDTO.getPosition().trim().isEmpty()) {
            throw new EmployeeValidationException("O cargo do funcionário é obrigatório");
        }
        if (employeeCreateDTO.getSalary() == null || employeeCreateDTO.getSalary().compareTo(BigDecimal.ZERO) <= 0) {
            throw new EmployeeValidationException("O salário do funcionário deve ser maior que zero");
        }
        if (employeeCreateDTO.getWorkCardNumber() == null || employeeCreateDTO.getWorkCardNumber().trim().isEmpty()) {
            throw new EmployeeValidationException("O número da carteira de trabalho é obrigatório");
        }
    }

    private void validateEmployeeUpdate(EmployeeUpdateDTO employeeUpdateDTO) {
        if (employeeUpdateDTO.getName() != null && employeeUpdateDTO.getName().trim().isEmpty()) {
            throw new EmployeeValidationException("O nome do funcionário não pode ser vazio");
        }
        if (employeeUpdateDTO.getEmail() != null && employeeUpdateDTO.getEmail().trim().isEmpty()) {
            throw new EmployeeValidationException("O email do funcionário não pode ser vazio");
        }
        if (employeeUpdateDTO.getPosition() != null && employeeUpdateDTO.getPosition().trim().isEmpty()) {
            throw new EmployeeValidationException("O cargo do funcionário não pode ser vazio");
        }
        if (employeeUpdateDTO.getSalary() != null && employeeUpdateDTO.getSalary().compareTo(BigDecimal.ZERO) <= 0) {
            throw new EmployeeValidationException("O salário do funcionário deve ser maior que zero");
        }
        if (employeeUpdateDTO.getWorkCardNumber() != null && employeeUpdateDTO.getWorkCardNumber().trim().isEmpty()) {
            throw new EmployeeValidationException("O número da carteira de trabalho não pode ser vazio");
        }
    }

    private Employee findEmployeeById(String id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Funcionário com ID " + id + " não encontrado"));
    }

    public void deleteEmployee(String id) {
        employeeRepository.deleteById(id);
    }

    private EmployeeResponseDTO convertToEmployeeResponseDTO(Employee employee) {
        EmployeeResponseDTO dto = new EmployeeResponseDTO();
        BeanUtils.copyProperties(employee, dto);
        return dto;
    }
}