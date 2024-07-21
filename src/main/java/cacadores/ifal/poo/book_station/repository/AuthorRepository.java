package cacadores.ifal.poo.book_station.repository;

import cacadores.ifal.poo.book_station.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, String> {
}