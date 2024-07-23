package cacadores.ifal.poo.book_station.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cacadores.ifal.poo.book_station.model.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    boolean existsByEmail(String email);

    boolean existsByWorkCardNumber(String workCardNumber);
}