package cacadores.ifal.poo.book_station.controller;

import cacadores.ifal.poo.book_station.model.entity.items.Book;
import cacadores.ifal.poo.book_station.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping("/book")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book savingBook = book;
        return new ResponseEntity<>(itemService.addBook(savingBook), HttpStatus.CREATED);
    }
}
