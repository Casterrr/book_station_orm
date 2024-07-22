package cacadores.ifal.poo.book_station.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cacadores.ifal.poo.book_station.model.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, String> {
    boolean existsByName(String name);
}