package cacadores.ifal.poo.book_station.service;

import cacadores.ifal.poo.book_station.exception.publisher.PublisherListEmptyException;
import cacadores.ifal.poo.book_station.exception.publisher.PublisherNotFoundException;
import cacadores.ifal.poo.book_station.model.entity.Publisher;
import cacadores.ifal.poo.book_station.repository.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public Publisher createPublisher(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    public Publisher getPublisherById(String id) {
        return publisherRepository.findById(id)
                .orElseThrow(() -> new PublisherNotFoundException("Publisher not found with id: " + id));
    }

    public List<Publisher> getAllPublishers() {
        // return publisherRepository.findAll();
        List<Publisher> publishers = publisherRepository.findAll();
        if (publishers.isEmpty()) {
            throw new PublisherListEmptyException("There's any publisher register");
        }
        return publishers;
    }

    public Publisher updatePublisher(Publisher publisher) {
        if (!publisherRepository.existsById(publisher.getId())) {
            throw new PublisherNotFoundException("Publisher not found with id: " + publisher.getId());
        }
        return publisherRepository.save(publisher);
    }

    public void deletePublisher(String id) {
        if (!publisherRepository.existsById(id)) {
            throw new PublisherNotFoundException("Publisher not found with id: " + id);
        }
        publisherRepository.deleteById(id);
    }
}