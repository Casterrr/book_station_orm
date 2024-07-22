package cacadores.ifal.poo.book_station.service;

import cacadores.ifal.poo.book_station.exception.genre.GenreAlreadyExistsException;
import cacadores.ifal.poo.book_station.exception.genre.GenreNotFoundException;
import cacadores.ifal.poo.book_station.exception.publisher.PublisherNotFoundException;
import cacadores.ifal.poo.book_station.model.entity.Genre;
import cacadores.ifal.poo.book_station.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public List<Genre> getAllGenres() {
        List<Genre> genres = genreRepository.findAll();
        if (genres.isEmpty()) {
            throw new GenreNotFoundException("Nenhum gênero encontrado");
        }
        return genres;
    }

    public Genre getGenreById(String id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new GenreNotFoundException("Genre not found with id: " + id));
    }

    public Genre createGenre(Genre genre) {
        if (genreRepository.findByNameIgnoreCase(genre.getName()).isPresent()) {
            throw new GenreAlreadyExistsException("Already exists a genre with this name: " + genre.getName());
        }
        return genreRepository.save(genre);
    }

    public Genre updateGenre(String id, Genre genreDetails) {
        Genre genre = genreRepository.findById(id)
            .orElseThrow(() -> new GenreNotFoundException("Genre not found with id: "+id));
        genre.setName(genreDetails.getName());
        genre.setCreationDate(genreDetails.getCreationDate());
        return genreRepository.save(genre);
    }

    public void deleteGenre(String id) {
        Genre genre = getGenreById(id);
        genreRepository.delete(genre);
    }
}