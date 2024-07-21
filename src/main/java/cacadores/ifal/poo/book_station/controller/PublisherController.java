package cacadores.ifal.poo.book_station.controller;

import cacadores.ifal.poo.book_station.model.entity.Publisher;
import cacadores.ifal.poo.book_station.service.PublisherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publishers")
@Tag(name = "Publisher", description = "APIs for managing publishers")
public class PublisherController {

    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @PostMapping
    @Operation(summary = "Create a new publisher", description = "Creates a new publisher entry")
    @ApiResponse(responseCode = "201", description = "Publisher created successfully")
    public ResponseEntity<Publisher> createPublisher(@RequestBody Publisher publisher) {
        return new ResponseEntity<>(publisherService.createPublisher(publisher), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a publisher by ID", description = "Retrieves a publisher by its ID")
    @ApiResponse(responseCode = "200", description = "Publisher found")
    @ApiResponse(responseCode = "404", description = "Publisher not found")
    public ResponseEntity<Publisher> getPublisherById(@PathVariable String id) {
        return ResponseEntity.ok(publisherService.getPublisherById(id));
    }

    @GetMapping
    @Operation(summary = "Get all publishers", description = "Retrieves a list of all publishers")
    @ApiResponse(responseCode = "200", description = "List of publishers retrieved successfully")
    public ResponseEntity<List<Publisher>> getAllPublishers() {
        return ResponseEntity.ok(publisherService.getAllPublishers());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a publisher", description = "Updates an existing publisher")
    @ApiResponse(responseCode = "200", description = "Publisher updated successfully")
    @ApiResponse(responseCode = "404", description = "Publisher not found")
    public ResponseEntity<Publisher> updatePublisher(@PathVariable String id, @RequestBody Publisher publisher) {
        publisher.setId(id);
        return ResponseEntity.ok(publisherService.updatePublisher(publisher));
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