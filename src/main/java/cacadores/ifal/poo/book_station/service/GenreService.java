package cacadores.ifal.poo.book_station.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
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

    public GenreResponseDTO addGenre(GenreCreateDTO genreCreateDTO) {
        if (genreRepository.existsByName(genreCreateDTO.getName())) {
            throw new GenreAlreadyExistsException("Um gênero com o nome " + genreCreateDTO.getName() + " já existe.");
        }
        Genre genre = new Genre();
        BeanUtils.copyProperties(genreCreateDTO, genre);
        genre = genreRepository.save(genre);
        return convertToGenreResponseDTO(genre);
    }

    public GenreResponseDTO updateGenre(Long id, GenreUpdateDTO genreUpdateDTO) {
        Genre existingGenre = findGenreEntityById(id);
        BeanUtils.copyProperties(genreUpdateDTO, existingGenre);
        existingGenre = genreRepository.save(existingGenre);
        return convertToGenreResponseDTO(existingGenre);
    }

    public GenreResponseDTO getGenreById(Long id) {
        Genre genre = findGenreEntityById(id);
        return convertToGenreResponseDTO(genre);
    }

    public List<GenreResponseDTO> getAllGenres() {
        return genreRepository.findAll().stream()
                .map(this::convertToGenreResponseDTO)
                .collect(Collectors.toList());
    }

    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }

    private Genre findGenreEntityById(Long id) {
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