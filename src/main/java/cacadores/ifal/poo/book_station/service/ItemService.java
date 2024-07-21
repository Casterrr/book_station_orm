package cacadores.ifal.poo.book_station.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cacadores.ifal.poo.book_station.dto.Book.BookCreateDTO;
import cacadores.ifal.poo.book_station.dto.Book.BookResponseDTO;
import cacadores.ifal.poo.book_station.dto.Book.BookUpdateDTO;
import cacadores.ifal.poo.book_station.dto.Magazine.MagazineCreateDTO;
import cacadores.ifal.poo.book_station.dto.Magazine.MagazineResponseDTO;
import cacadores.ifal.poo.book_station.dto.Magazine.MagazineUpdateDTO;
import cacadores.ifal.poo.book_station.exception.BookAlreadyExistsException;
import cacadores.ifal.poo.book_station.exception.BookNotFoundException;
import cacadores.ifal.poo.book_station.exception.MagazineAlreadyExistsException;
import cacadores.ifal.poo.book_station.exception.MagazineNotFoundException;
import cacadores.ifal.poo.book_station.model.entity.items.Book;
import cacadores.ifal.poo.book_station.model.entity.items.Magazine;
import cacadores.ifal.poo.book_station.repository.BookRepository;
import cacadores.ifal.poo.book_station.repository.MagazineRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ItemService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    MagazineRepository magazineRepository;

    public BookResponseDTO addBook(BookCreateDTO bookCreateDTO) {
        if (bookRepository.existsByIsbn(bookCreateDTO.getIsbn())) {
            throw new BookAlreadyExistsException("Um livro com o ISBN " + bookCreateDTO.getIsbn() + " já existe.");
        }
        Book book = new Book();
        BeanUtils.copyProperties(bookCreateDTO, book);
        // Definir publisher, author e genre baseado nos IDs fornecidos
        book = bookRepository.save(book);
        return convertToBookResponseDTO(book);
    }

    public BookResponseDTO updateBook(String id, BookUpdateDTO bookUpdateDTO) {
        Book existingBook = findBookEntityById(id);
        BeanUtils.copyProperties(bookUpdateDTO, existingBook);
        // Atualizar publisher, author e genre se necessário
        existingBook = bookRepository.save(existingBook);
        return convertToBookResponseDTO(existingBook);
    }

    private Book findBookEntityById(String id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Livro com ID " + id + " não encontrado."));
    }

    public MagazineResponseDTO addMagazine(MagazineCreateDTO magazineCreateDTO) {
        if (magazineRepository.existsByIssn(magazineCreateDTO.getIssn())) {
            throw new MagazineAlreadyExistsException("Uma revista com o ISSN " + magazineCreateDTO.getIssn() + " já existe.");
        }
        Magazine magazine = new Magazine();
        BeanUtils.copyProperties(magazineCreateDTO, magazine);
        // Definir publisher e genre baseado nos IDs fornecidos
        magazine = magazineRepository.save(magazine);
        return convertToMagazineResponseDTO(magazine);
    }

    public MagazineResponseDTO updateMagazine(String id, MagazineUpdateDTO magazineUpdateDTO) {
        Magazine existingMagazine = findMagazineEntityById(id);
        BeanUtils.copyProperties(magazineUpdateDTO, existingMagazine);
        // Atualizar publisher e genre se necessário
        existingMagazine = magazineRepository.save(existingMagazine);
        return convertToMagazineResponseDTO(existingMagazine);
    }

    public BookResponseDTO getBookById(String id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Livro com ID " + id + " não encontrado."));
        return convertToBookResponseDTO(book);
    }

    public List<BookResponseDTO> getBooks() {
        return bookRepository.findAll().stream()
                .map(this::convertToBookResponseDTO)
                .collect(Collectors.toList());
    }

    private BookResponseDTO convertToBookResponseDTO(Book book) {
        BookResponseDTO dto = new BookResponseDTO();
        BeanUtils.copyProperties(book, dto);
        dto.setPublisherName(book.getPublisher() != null ? book.getPublisher().getName() : null);
        dto.setAuthorName(book.getAuthor() != null ? book.getAuthor().getName() : null);
        dto.setGenreName(book.getGenre() != null ? book.getGenre().getName() : null);
        return dto;
    }

    public MagazineResponseDTO getMagazineById(String id) {
        Magazine magazine = findMagazineEntityById(id);
        return convertToMagazineResponseDTO(magazine);
    }

    public List<MagazineResponseDTO> getMagazines() {
        return magazineRepository.findAll().stream()
                .map(this::convertToMagazineResponseDTO)
                .collect(Collectors.toList());
    }

    private Magazine findMagazineEntityById(String id) {
        return magazineRepository.findById(id)
                .orElseThrow(() -> new MagazineNotFoundException("Revista com ID " + id + " não encontrada."));
    }

    private MagazineResponseDTO convertToMagazineResponseDTO(Magazine magazine) {
        MagazineResponseDTO dto = new MagazineResponseDTO();
        BeanUtils.copyProperties(magazine, dto);
        dto.setPublisherName(magazine.getPublisher() != null ? magazine.getPublisher().getName() : null);
        dto.setGenreName(magazine.getGenre() != null ? magazine.getGenre().getName() : null);
        return dto;
    }

    public void deleteBook(String id) {
        bookRepository.removeById(id);
    }

    public void deleteMagazine(String id) {
        magazineRepository.removeById(id);
    }
}
