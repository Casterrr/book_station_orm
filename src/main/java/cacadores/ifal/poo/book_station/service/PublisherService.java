package cacadores.ifal.poo.book_station.service;

import java.util.List;
import java.util.stream.Collectors;

import cacadores.ifal.poo.book_station.exception.PublisherNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cacadores.ifal.poo.book_station.dto.Publisher.PublisherCreateDTO;
import cacadores.ifal.poo.book_station.dto.Publisher.PublisherResponseDTO;
import cacadores.ifal.poo.book_station.dto.Publisher.PublisherUpdateDTO;
import cacadores.ifal.poo.book_station.exception.PublisherAlreadyExistsException;
import cacadores.ifal.poo.book_station.model.entity.Publisher;
import cacadores.ifal.poo.book_station.repository.PublisherRepository;


@Service
public class PublisherService {
    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public PublisherResponseDTO addPublisher(PublisherCreateDTO publisherCreateDTO) {
        if (publisherRepository.existsByName(publisherCreateDTO.getName())) {
            throw new PublisherAlreadyExistsException(
                    "Uma editora com o nome " + publisherCreateDTO.getName() + " já existe.");
        }
        Publisher publisher = new Publisher();
        BeanUtils.copyProperties(publisherCreateDTO, publisher);
        publisher = publisherRepository.save(publisher);
        return convertToPublisherResponseDTO(publisher);
    }

    public PublisherResponseDTO getPublisherById(Long id) {
        Publisher publisher = findPublisherEntityById(id);
        return convertToPublisherResponseDTO(publisher);
    }

    public List<PublisherResponseDTO> getAllPublishers() {
        return publisherRepository.findAll().stream()
                .map(this::convertToPublisherResponseDTO)
                .collect(Collectors.toList());
    }

    public PublisherResponseDTO updatePublisher(Long id, PublisherUpdateDTO publisherUpdateDTO) {
        Publisher existingPublisher = findPublisherEntityById(id);
        BeanUtils.copyProperties(publisherUpdateDTO, existingPublisher);
        existingPublisher = publisherRepository.save(existingPublisher);
        return convertToPublisherResponseDTO(existingPublisher);
    }

    public void deletePublisher(Long id) {
        publisherRepository.deleteById(id);
    }

    private Publisher findPublisherEntityById(Long id) {
        return publisherRepository.findById(id)
                .orElseThrow(() -> new PublisherNotFoundException("Editora com ID " + id + " não encontrada."));
    }

    private PublisherResponseDTO convertToPublisherResponseDTO(Publisher publisher) {
        PublisherResponseDTO dto = new PublisherResponseDTO();
        BeanUtils.copyProperties(publisher, dto);
        return dto;
    }
}