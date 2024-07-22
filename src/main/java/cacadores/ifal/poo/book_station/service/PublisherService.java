package cacadores.ifal.poo.book_station.service;

<<<<<<< HEAD
import cacadores.ifal.poo.book_station.exception.publisher.PublisherListEmptyException;
import cacadores.ifal.poo.book_station.exception.publisher.PublisherNotFoundException;
=======
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cacadores.ifal.poo.book_station.dto.Publisher.PublisherCreateDTO;
import cacadores.ifal.poo.book_station.dto.Publisher.PublisherResponseDTO;
import cacadores.ifal.poo.book_station.dto.Publisher.PublisherUpdateDTO;
import cacadores.ifal.poo.book_station.exception.PublisherAlreadyExistsException;
import cacadores.ifal.poo.book_station.exception.PublisherNotFoundException;
>>>>>>> main
import cacadores.ifal.poo.book_station.model.entity.Publisher;
import cacadores.ifal.poo.book_station.repository.PublisherRepository;

@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

<<<<<<< HEAD
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
=======
    public PublisherResponseDTO addPublisher(PublisherCreateDTO publisherCreateDTO) {
        if (publisherRepository.existsByName(publisherCreateDTO.getName())) {
            throw new PublisherAlreadyExistsException("Uma editora com o nome " + publisherCreateDTO.getName() + " já existe.");
>>>>>>> main
        }
        Publisher publisher = new Publisher();
        BeanUtils.copyProperties(publisherCreateDTO, publisher);
        publisher = publisherRepository.save(publisher);
        return convertToPublisherResponseDTO(publisher);
    }

    public PublisherResponseDTO getPublisherById(String id) {
        Publisher publisher = findPublisherEntityById(id);
        return convertToPublisherResponseDTO(publisher);
    }

    public List<PublisherResponseDTO> getAllPublishers() {
        return publisherRepository.findAll().stream()
                .map(this::convertToPublisherResponseDTO)
                .collect(Collectors.toList());
    }

    public PublisherResponseDTO updatePublisher(String id, PublisherUpdateDTO publisherUpdateDTO) {
        Publisher existingPublisher = findPublisherEntityById(id);
        BeanUtils.copyProperties(publisherUpdateDTO, existingPublisher);
        existingPublisher = publisherRepository.save(existingPublisher);
        return convertToPublisherResponseDTO(existingPublisher);
    }

    public void deletePublisher(String id) {
        publisherRepository.deleteById(id);
    }

    private Publisher findPublisherEntityById(String id) {
        return publisherRepository.findById(id)
                .orElseThrow(() -> new PublisherNotFoundException("Editora com ID " + id + " não encontrada."));
    }

    private PublisherResponseDTO convertToPublisherResponseDTO(Publisher publisher) {
        PublisherResponseDTO dto = new PublisherResponseDTO();
        BeanUtils.copyProperties(publisher, dto);
        return dto;
    }
}