package cacadores.ifal.poo.book_station.service;

import cacadores.ifal.poo.book_station.model.entity.Genre;
import cacadores.ifal.poo.book_station.repository.GenreRepository;
import cacadores.ifal.poo.book_station.exception.GenreNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public Genre getGenreById(String id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new GenreNotFoundException("Genre not found with id: " + id));
    }

    public Genre createGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    public Genre updateGenre(String id, Genre genreDetails) {
        Genre genre = getGenreById(id);
        genre.setName(genreDetails.getName());
        genre.setCreationDate(genreDetails.getCreationDate());
        return genreRepository.save(genre);
    }

    public void deleteGenre(String id) {
        Genre genre = getGenreById(id);
        genreRepository.delete(genre);
    }
}