package cacadores.ifal.poo.book_station.controller;

import java.util.List;

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

import cacadores.ifal.poo.book_station.dto.Publisher.PublisherCreateDTO;
import cacadores.ifal.poo.book_station.dto.Publisher.PublisherResponseDTO;
import cacadores.ifal.poo.book_station.dto.Publisher.PublisherUpdateDTO;
import cacadores.ifal.poo.book_station.service.PublisherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/publishers")
@Tag(name = "Publisher", description = "APIs for managing publishers")
public class PublisherController {

    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @PostMapping()
    @Operation(summary = "Create a new publisher", description = "Creates a new publisher entry")
    @ApiResponse(responseCode = "201", description = "Publisher created successfully")
    public ResponseEntity<PublisherResponseDTO> createPublisher(@RequestBody PublisherCreateDTO publisherCreateDTO) {
        return new ResponseEntity<>(publisherService.addPublisher(publisherCreateDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a publisher by ID", description = "Retrieves a publisher by its ID")
    @ApiResponse(responseCode = "200", description = "Publisher found")
    @ApiResponse(responseCode = "404", description = "Publisher not found")
    public ResponseEntity<PublisherResponseDTO> getPublisherById(@PathVariable String id) {
        return new ResponseEntity<>(publisherService.getPublisherById(id), HttpStatus.OK);
    }

    @GetMapping()
    @Operation(summary = "Get all publishers", description = "Retrieves a list of all publishers")
    @ApiResponse(responseCode = "200", description = "List of publishers retrieved successfully")
    public ResponseEntity<List<PublisherResponseDTO>> getAllPublishers() {
        return new ResponseEntity<>(publisherService.getAllPublishers(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a publisher", description = "Updates an existing publisher")
    @ApiResponse(responseCode = "200", description = "Publisher updated successfully")
    @ApiResponse(responseCode = "404", description = "Publisher not found")
    public ResponseEntity<PublisherResponseDTO> updatePublisher(@PathVariable String id, @RequestBody PublisherUpdateDTO publisherUpdateDTO) {
        return new ResponseEntity<>(publisherService.updatePublisher(id, publisherUpdateDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a publisher", description = "Deletes a publisher by its ID")
    @ApiResponse(responseCode = "204", description = "Publisher deleted successfully")
    @ApiResponse(responseCode = "404", description = "Publisher not found")
    public ResponseEntity<Void> deletePublisher(@PathVariable String id) {
        publisherService.deletePublisher(id);
        return ResponseEntity.noContent().build();
    }
}