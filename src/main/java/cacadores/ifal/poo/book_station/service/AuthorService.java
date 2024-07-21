package cacadores.ifal.poo.book_station.service;

import cacadores.ifal.poo.book_station.model.entity.Author;
import cacadores.ifal.poo.book_station.repository.AuthorRepository;
import cacadores.ifal.poo.book_station.exception.AuthorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(String id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException("Author not found with id: " + id));
    }

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Author updateAuthor(String id, Author authorDetails) {
        Author author = getAuthorById(id);
        author.setName(authorDetails.getName());
        author.setBirthDate(authorDetails.getBirthDate());
        author.setBiography(authorDetails.getBiography());
        return authorRepository.save(author);
    }

    public void deleteAuthor(String id) {
        Author author = getAuthorById(id);
        authorRepository.delete(author);
    }
}