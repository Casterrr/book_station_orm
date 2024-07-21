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

import cacadores.ifal.poo.book_station.dto.Author.AuthorCreateDTO;
import cacadores.ifal.poo.book_station.dto.Author.AuthorResponseDTO;
import cacadores.ifal.poo.book_station.dto.Author.AuthorUpdateDTO;
import cacadores.ifal.poo.book_station.service.AuthorService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/authors")
@Tag(name = "Author", description = "APIs for managing authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping()
    public ResponseEntity<List<AuthorResponseDTO>> getAllAuthors() {
        return new ResponseEntity<>(authorService.getAllAuthors(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> getAuthorById(@PathVariable String id) {
        return new ResponseEntity<>(authorService.getAuthorById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<AuthorResponseDTO> createAuthor(@RequestBody AuthorCreateDTO authorCreateDTO) {
        return new ResponseEntity<>(authorService.addAuthor(authorCreateDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> updateAuthor(@PathVariable String id, @RequestBody AuthorUpdateDTO authorUpdateDTO) {
        return new ResponseEntity<>(authorService.updateAuthor(id, authorUpdateDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable String id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }
}