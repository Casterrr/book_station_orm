package cacadores.ifal.poo.book_station.controller;

import cacadores.ifal.poo.book_station.exception.BookNotFoundException;
import cacadores.ifal.poo.book_station.model.entity.items.Book;
import cacadores.ifal.poo.book_station.service.ItemService;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
@Tag(name = "Book Management", description = "APIs for managing books")
public class BookController {
    @Autowired
    private ItemService itemService;

    @PostMapping()
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        return new ResponseEntity<>(itemService.addBook(book), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable String id) {
        return new ResponseEntity<>(itemService.getBookById(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<List<Book>>(itemService.getBooks(), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<Book> updateBook(@RequestBody Book book) {
        if (book.getId() == null)
            throw new BookNotFoundException("Book not found");
        return new ResponseEntity<>(itemService.addBook(book), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable String id) {
        final Book book = itemService.getBookById(id);

        if (book == null) {
            throw new BookNotFoundException("Book not found");
        }

        itemService.deleteBook(id);

        return new ResponseEntity<>(book, HttpStatus.OK);
    }
}
