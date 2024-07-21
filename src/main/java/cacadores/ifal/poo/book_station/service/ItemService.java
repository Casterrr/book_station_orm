package cacadores.ifal.poo.book_station.service;

import cacadores.ifal.poo.book_station.model.entity.items.Book;
import cacadores.ifal.poo.book_station.model.entity.items.Magazine;
import cacadores.ifal.poo.book_station.repository.BookRepository;
import cacadores.ifal.poo.book_station.repository.MagazineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    MagazineRepository magazineRepository;

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Magazine addMagazine(Magazine magazine) {
        return magazineRepository.save(magazine);
    }
}
