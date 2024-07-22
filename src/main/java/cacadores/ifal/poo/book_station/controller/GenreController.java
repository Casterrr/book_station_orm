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

import cacadores.ifal.poo.book_station.dto.Genre.GenreCreateDTO;
import cacadores.ifal.poo.book_station.dto.Genre.GenreResponseDTO;
import cacadores.ifal.poo.book_station.dto.Genre.GenreUpdateDTO;
import cacadores.ifal.poo.book_station.service.GenreService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/genres")
@Tag(name = "Genre", description = "API for managing genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping()
    public ResponseEntity<List<GenreResponseDTO>> getAllGenres() {
        return new ResponseEntity<>(genreService.getAllGenres(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreResponseDTO> getGenreById(@PathVariable Long id) {
        return new ResponseEntity<>(genreService.getGenreById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<GenreResponseDTO> createGenre(@RequestBody GenreCreateDTO genreCreateDTO) {
        return new ResponseEntity<>(genreService.addGenre(genreCreateDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenreResponseDTO> updateGenre(@PathVariable Long id,
            @RequestBody GenreUpdateDTO genreUpdateDTO) {
        return new ResponseEntity<>(genreService.updateGenre(id, genreUpdateDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
        genreService.deleteGenre(id);
        return ResponseEntity.noContent().build();
    }
}