package cacadores.ifal.poo.book_station.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cacadores.ifal.poo.book_station.dto.Book.BookCreateDTO;
import cacadores.ifal.poo.book_station.dto.Book.BookResponseDTO;
import cacadores.ifal.poo.book_station.dto.Book.BookUpdateDTO;
import cacadores.ifal.poo.book_station.service.ItemService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/book")
@Tag(name = "Book", description = "APIs for managing books")
public class BookController {
    @Autowired
    private ItemService itemService;

    @PostMapping()
    public ResponseEntity<BookResponseDTO> createBook(@RequestBody BookCreateDTO bookCreateDTO) {
        return new ResponseEntity<>(itemService.addBook(bookCreateDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable String id) {
        return new ResponseEntity<>(itemService.getBookById(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<BookResponseDTO>> getAllBooks() {
        return new ResponseEntity<>(itemService.getBooks(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable String id, @RequestBody BookUpdateDTO bookUpdateDTO) {
        return new ResponseEntity<>(itemService.updateBook(id, bookUpdateDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable String id) {
        itemService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
