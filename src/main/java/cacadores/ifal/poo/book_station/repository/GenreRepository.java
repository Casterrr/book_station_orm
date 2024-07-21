package cacadores.ifal.poo.book_station.repository;

import cacadores.ifal.poo.book_station.model.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, String> {
}