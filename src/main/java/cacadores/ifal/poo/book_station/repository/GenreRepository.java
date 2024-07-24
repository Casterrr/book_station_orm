package cacadores.ifal.poo.book_station.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cacadores.ifal.poo.book_station.model.entity.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
  boolean existsByNameIgnoreCase(String name);
}