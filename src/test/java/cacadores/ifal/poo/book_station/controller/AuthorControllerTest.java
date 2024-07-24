package cacadores.ifal.poo.book_station.controller;

import cacadores.ifal.poo.book_station.dto.Author.AuthorCreateDTO;
import cacadores.ifal.poo.book_station.dto.Author.AuthorResponseDTO;
import cacadores.ifal.poo.book_station.dto.Author.AuthorUpdateDTO;
import cacadores.ifal.poo.book_station.service.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AuthorControllerTest {

    @Mock
    private AuthorService authorService;

    @InjectMocks
    private AuthorController authorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllAuthors() {
        List<AuthorResponseDTO> authors = Arrays.asList(new AuthorResponseDTO(), new AuthorResponseDTO());
        when(authorService.getAllAuthors()).thenReturn(authors);

        ResponseEntity<List<AuthorResponseDTO>> response = authorController.getAllAuthors();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(authors, response.getBody());
    }

    @Test
    void getAuthorById() {
        Long id = 1L;
        AuthorResponseDTO author = new AuthorResponseDTO();
        when(authorService.getAuthorById(id)).thenReturn(author);

        ResponseEntity<AuthorResponseDTO> response = authorController.getAuthorById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(author, response.getBody());
    }

    @Test
    void createAuthor() {
        AuthorCreateDTO createDTO = new AuthorCreateDTO();
        AuthorResponseDTO responseDTO = new AuthorResponseDTO();
        when(authorService.addAuthor(createDTO)).thenReturn(responseDTO);

        ResponseEntity<AuthorResponseDTO> response = authorController.createAuthor(createDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
    }

    @Test
    void updateAuthor() {
        Long id = 1L;
        AuthorUpdateDTO updateDTO = new AuthorUpdateDTO();
        AuthorResponseDTO responseDTO = new AuthorResponseDTO();
        when(authorService.updateAuthor(id, updateDTO)).thenReturn(responseDTO);

        ResponseEntity<AuthorResponseDTO> response = authorController.updateAuthor(id, updateDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
    }

    @Test
    void deleteAuthor() {
        Long id = 1L;

        ResponseEntity<Void> response = authorController.deleteAuthor(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(authorService, times(1)).deleteAuthor(id);
    }
}