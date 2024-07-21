package cacadores.ifal.poo.book_station.repository;

import cacadores.ifal.poo.book_station.model.entity.items.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {
    Book save(Book book);
    Book findBookById(String id);
    List<Book> findAll();
    void removeById(String id);
}
