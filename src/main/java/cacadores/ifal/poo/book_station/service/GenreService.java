package cacadores.ifal.poo.book_station.service;

<<<<<<< HEAD
import cacadores.ifal.poo.book_station.exception.genre.GenreAlreadyExistsException;
import cacadores.ifal.poo.book_station.exception.genre.GenreNotFoundException;
import cacadores.ifal.poo.book_station.exception.publisher.PublisherNotFoundException;
import cacadores.ifal.poo.book_station.model.entity.Genre;
import cacadores.ifal.poo.book_station.repository.GenreRepository;
=======
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
>>>>>>> main
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cacadores.ifal.poo.book_station.dto.Genre.GenreCreateDTO;
import cacadores.ifal.poo.book_station.dto.Genre.GenreResponseDTO;
import cacadores.ifal.poo.book_station.dto.Genre.GenreUpdateDTO;
import cacadores.ifal.poo.book_station.exception.GenreAlreadyExistsException;
import cacadores.ifal.poo.book_station.exception.GenreNotFoundException;
import cacadores.ifal.poo.book_station.model.entity.Genre;
import cacadores.ifal.poo.book_station.repository.GenreRepository;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

<<<<<<< HEAD
    public List<Genre> getAllGenres() {
        List<Genre> genres = genreRepository.findAll();
        if (genres.isEmpty()) {
            throw new GenreNotFoundException("Nenhum gênero encontrado");
        }
        return genres;
=======
    public GenreResponseDTO addGenre(GenreCreateDTO genreCreateDTO) {
        if (genreRepository.existsByName(genreCreateDTO.getName())) {
            throw new GenreAlreadyExistsException("Um gênero com o nome " + genreCreateDTO.getName() + " já existe.");
        }
        Genre genre = new Genre();
        BeanUtils.copyProperties(genreCreateDTO, genre);
        genre = genreRepository.save(genre);
        return convertToGenreResponseDTO(genre);
>>>>>>> main
    }

    public GenreResponseDTO updateGenre(String id, GenreUpdateDTO genreUpdateDTO) {
        Genre existingGenre = findGenreEntityById(id);
        BeanUtils.copyProperties(genreUpdateDTO, existingGenre);
        existingGenre = genreRepository.save(existingGenre);
        return convertToGenreResponseDTO(existingGenre);
    }

<<<<<<< HEAD
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
=======
    public GenreResponseDTO getGenreById(String id) {
        Genre genre = findGenreEntityById(id);
        return convertToGenreResponseDTO(genre);
    }

    public List<GenreResponseDTO> getAllGenres() {
        return genreRepository.findAll().stream()
                .map(this::convertToGenreResponseDTO)
                .collect(Collectors.toList());
>>>>>>> main
    }

    public void deleteGenre(String id) {
        genreRepository.deleteById(id);
    }

    private Genre findGenreEntityById(String id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new GenreNotFoundException("Gênero com ID " + id + " não encontrado."));
    }

    private GenreResponseDTO convertToGenreResponseDTO(Genre genre) {
        GenreResponseDTO dto = new GenreResponseDTO();
        BeanUtils.copyProperties(genre, dto);
        dto.setCreationDate(genre.getCreationDate());
        return dto;
    }
}