package cacadores.ifal.poo.book_station.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, String> {
    Optional<Genre> findByNameIgnoreCase(String name);
=======
import cacadores.ifal.poo.book_station.model.entity.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, String> {
  boolean existsByName(String name);
>>>>>>> main
}