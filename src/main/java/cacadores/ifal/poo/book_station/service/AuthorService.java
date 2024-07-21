package cacadores.ifal.poo.book_station.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cacadores.ifal.poo.book_station.dto.Author.AuthorCreateDTO;
import cacadores.ifal.poo.book_station.dto.Author.AuthorResponseDTO;
import cacadores.ifal.poo.book_station.dto.Author.AuthorUpdateDTO;
import cacadores.ifal.poo.book_station.exception.AuthorAlreadyExistsException;
import cacadores.ifal.poo.book_station.exception.AuthorNotFoundException;
import cacadores.ifal.poo.book_station.model.entity.Author;
import cacadores.ifal.poo.book_station.repository.AuthorRepository;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<AuthorResponseDTO> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(this::convertToAuthorResponseDTO)
                .collect(Collectors.toList());
    }

    public AuthorResponseDTO getAuthorById(String id) {
        Author author = findAuthorEntityById(id);
        return convertToAuthorResponseDTO(author);
    }

    public AuthorResponseDTO addAuthor(AuthorCreateDTO authorCreateDTO) {
        if (authorRepository.existsByName(authorCreateDTO.getName())) {
            throw new AuthorAlreadyExistsException("Um autor com o nome " + authorCreateDTO.getName() + " já existe.");
        }
        if (authorCreateDTO.getBirthDate() == null) {
            throw new IllegalArgumentException("A data de nascimento não pode ser nula.");
        }
        Author author = new Author();
        BeanUtils.copyProperties(authorCreateDTO, author);
        author = authorRepository.save(author);
        return convertToAuthorResponseDTO(author);
    }

    public AuthorResponseDTO updateAuthor(String id, AuthorUpdateDTO authorUpdateDTO) {
        Author existingAuthor = findAuthorEntityById(id);
        if (authorUpdateDTO.getBirthDate() == null) {
            throw new IllegalArgumentException("A data de nascimento não pode ser nula.");
        }
        BeanUtils.copyProperties(authorUpdateDTO, existingAuthor);
        existingAuthor = authorRepository.save(existingAuthor);
        return convertToAuthorResponseDTO(existingAuthor);
    }

    public void deleteAuthor(String id) {
        authorRepository.deleteById(id);
    }

    private Author findAuthorEntityById(String id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException("Autor com ID " + id + " não encontrado."));
    }

    private AuthorResponseDTO convertToAuthorResponseDTO(Author author) {
        AuthorResponseDTO dto = new AuthorResponseDTO();
        BeanUtils.copyProperties(author, dto);
        return dto;
    }
}