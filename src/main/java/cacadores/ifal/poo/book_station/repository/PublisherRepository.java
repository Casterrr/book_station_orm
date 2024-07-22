package cacadores.ifal.poo.book_station.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cacadores.ifal.poo.book_station.model.entity.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, String> {
  boolean existsByName(String name);
}