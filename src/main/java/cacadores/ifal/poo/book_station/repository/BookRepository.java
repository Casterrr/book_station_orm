package cacadores.ifal.poo.book_station.repository;

import cacadores.ifal.poo.book_station.model.entity.items.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {
    Book save(Book book);
}
