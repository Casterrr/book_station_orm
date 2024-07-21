package cacadores.ifal.poo.book_station.controller;

import cacadores.ifal.poo.book_station.model.entity.Genre;
import cacadores.ifal.poo.book_station.service.GenreService;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
@Tag(name = "Genres", description = "API for managing genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping
    public List<Genre> getAllGenres() {
        return genreService.getAllGenres();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable String id) {
        Genre genre = genreService.getGenreById(id);
        return ResponseEntity.ok(genre);
    }

    @PostMapping
    public ResponseEntity<Genre> createGenre(@RequestBody Genre genre) {
        Genre newGenre = genreService.createGenre(genre);
        return ResponseEntity.ok(newGenre);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genre> updateGenre(@PathVariable String id, @RequestBody Genre genreDetails) {
        Genre updatedGenre = genreService.updateGenre(id, genreDetails);
        return ResponseEntity.ok(updatedGenre);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable String id) {
        genreService.deleteGenre(id);
        return ResponseEntity.noContent().build();
    }
}