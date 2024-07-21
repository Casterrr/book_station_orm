package cacadores.ifal.poo.book_station.service;

import cacadores.ifal.poo.book_station.exception.BookAlreadyExistsException;
import cacadores.ifal.poo.book_station.exception.BookNotFoundException;
import cacadores.ifal.poo.book_station.exception.MagazineAlreadyExistsException;
import cacadores.ifal.poo.book_station.exception.MagazineNotFoundException;
import cacadores.ifal.poo.book_station.model.entity.items.Book;
import cacadores.ifal.poo.book_station.model.entity.items.Magazine;
import cacadores.ifal.poo.book_station.repository.BookRepository;
import cacadores.ifal.poo.book_station.repository.MagazineRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ItemService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    MagazineRepository magazineRepository;

    public Book addBook(Book book) {
        if (book.getId() != null && bookRepository.existsById(book.getId())) {
            throw new BookAlreadyExistsException("Livro com ID " + book.getId() + " já existe.");
        }
        return bookRepository.save(book);
    }

    public Book updateBook(String id, Book book) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException("Livro com ID " + id + " não encontrado.");
        }
        book.setId(id);
        return bookRepository.save(book);
    }

    public Magazine addMagazine(Magazine magazine) {
        if (magazine.getId() != null && magazineRepository.existsById(magazine.getId())) {
            throw new MagazineAlreadyExistsException("Revista com ID " + magazine.getId() + " já existe.");
        }
        return magazineRepository.save(magazine);
    }

    public Magazine updateMagazine(String id, Magazine magazine) {
        if (!magazineRepository.existsById(id)) {
            throw new MagazineNotFoundException("Revista com ID " + id + " não encontrada.");
        }
        magazine.setId(id);
        return magazineRepository.save(magazine);
    }

    public Book getBookById(String id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Livro com ID " + id + " não encontrado."));
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Magazine getMagazineById(String id) {
        return magazineRepository.findById(id)
                .orElseThrow(() -> new MagazineNotFoundException("Revista com ID " + id + " não encontrada."));
    }

    public List<Magazine> getMagazines() {
        return magazineRepository.findAll();
    }

    public void deleteBook(String id) {
        bookRepository.removeById(id);
    }

    public void deleteMagazine(String id) {
        magazineRepository.removeById(id);
    }
}
