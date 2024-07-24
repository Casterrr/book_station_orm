package cacadores.ifal.poo.book_station.controller;

import cacadores.ifal.poo.book_station.dto.Book.BookCreateDTO;
import cacadores.ifal.poo.book_station.dto.Book.BookResponseDTO;
import cacadores.ifal.poo.book_station.dto.Book.BookUpdateDTO;
import cacadores.ifal.poo.book_station.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookControllerTest {

    @Mock
    private ItemService itemService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateBook() {
        BookCreateDTO bookCreateDTO = new BookCreateDTO();
        BookResponseDTO bookResponseDTO = new BookResponseDTO();
        when(itemService.addBook(bookCreateDTO)).thenReturn(bookResponseDTO);

        ResponseEntity<BookResponseDTO> response = bookController.createBook(bookCreateDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(bookResponseDTO, response.getBody());
        verify(itemService).addBook(bookCreateDTO);
    }

    @Test
    void testGetBookById() {
        String id = "1";
        BookResponseDTO bookResponseDTO = new BookResponseDTO();
        when(itemService.getBookById(id)).thenReturn(bookResponseDTO);

        ResponseEntity<BookResponseDTO> response = bookController.getBookById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bookResponseDTO, response.getBody());
        verify(itemService).getBookById(id);
    }

    @Test
    void testGetAllBooks() {
        List<BookResponseDTO> books = Arrays.asList(new BookResponseDTO(), new BookResponseDTO());
        when(itemService.getBooks()).thenReturn(books);

        ResponseEntity<List<BookResponseDTO>> response = bookController.getAllBooks();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(books, response.getBody());
        assertEquals(2, response.getBody().size());
        verify(itemService).getBooks();
    }

    @Test
    void testUpdateBook() {
        String id = "1";
        BookUpdateDTO bookUpdateDTO = new BookUpdateDTO();
        BookResponseDTO bookResponseDTO = new BookResponseDTO();
        when(itemService.updateBook(id, bookUpdateDTO)).thenReturn(bookResponseDTO);

        ResponseEntity<BookResponseDTO> response = bookController.updateBook(id, bookUpdateDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bookResponseDTO, response.getBody());
        verify(itemService).updateBook(id, bookUpdateDTO);
    }

    @Test
    void testDeleteBook() {
        String id = "1";
        doNothing().when(itemService).deleteBook(id);

        ResponseEntity<Void> response = bookController.deleteBook(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(itemService).deleteBook(id);
    }
}