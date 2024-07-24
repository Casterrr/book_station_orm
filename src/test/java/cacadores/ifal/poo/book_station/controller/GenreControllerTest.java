package cacadores.ifal.poo.book_station.controller;

import cacadores.ifal.poo.book_station.dto.Genre.GenreCreateDTO;
import cacadores.ifal.poo.book_station.dto.Genre.GenreResponseDTO;
import cacadores.ifal.poo.book_station.dto.Genre.GenreUpdateDTO;
import cacadores.ifal.poo.book_station.service.GenreService;
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

class GenreControllerTest {

    @Mock
    private GenreService genreService;

    @InjectMocks
    private GenreController genreController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllGenres() {
        List<GenreResponseDTO> genres = Arrays.asList(new GenreResponseDTO(), new GenreResponseDTO());
        when(genreService.getAllGenres()).thenReturn(genres);

        ResponseEntity<List<GenreResponseDTO>> response = genreController.getAllGenres();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(genres, response.getBody());
        assertEquals(2, response.getBody().size());
        verify(genreService).getAllGenres();
    }

    @Test
    void testGetGenreById() {
        Long id = 1L;
        GenreResponseDTO genreResponseDTO = new GenreResponseDTO();
        when(genreService.getGenreById(id)).thenReturn(genreResponseDTO);

        ResponseEntity<GenreResponseDTO> response = genreController.getGenreById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(genreResponseDTO, response.getBody());
        verify(genreService).getGenreById(id);
    }

    @Test
    void testCreateGenre() {
        GenreCreateDTO genreCreateDTO = new GenreCreateDTO();
        GenreResponseDTO genreResponseDTO = new GenreResponseDTO();
        when(genreService.addGenre(genreCreateDTO)).thenReturn(genreResponseDTO);

        ResponseEntity<GenreResponseDTO> response = genreController.createGenre(genreCreateDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(genreResponseDTO, response.getBody());
        verify(genreService).addGenre(genreCreateDTO);
    }

    @Test
    void testUpdateGenre() {
        Long id = 1L;
        GenreUpdateDTO genreUpdateDTO = new GenreUpdateDTO();
        GenreResponseDTO genreResponseDTO = new GenreResponseDTO();
        when(genreService.updateGenre(id, genreUpdateDTO)).thenReturn(genreResponseDTO);

        ResponseEntity<GenreResponseDTO> response = genreController.updateGenre(id, genreUpdateDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(genreResponseDTO, response.getBody());
        verify(genreService).updateGenre(id, genreUpdateDTO);
    }

    @Test
    void testDeleteGenre() {
        Long id = 1L;
        doNothing().when(genreService).deleteGenre(id);

        ResponseEntity<Void> response = genreController.deleteGenre(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(genreService).deleteGenre(id);
    }
}