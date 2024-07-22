package cacadores.ifal.poo.book_station.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cacadores.ifal.poo.book_station.model.entity.Tenant;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, String> {
  boolean existsByEmail(String email);
  boolean existsByCpf(String cpf);
}