package cacadores.ifal.poo.book_station.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cacadores.ifal.poo.book_station.model.entity.items.Book;

public interface BookRepository extends JpaRepository<Book, String> {
    Book save(Book book);
    Book findBookById(String id);
    boolean existsByIsbn(String isbn);
    List<Book> findAll();
    void removeById(String id);
}
