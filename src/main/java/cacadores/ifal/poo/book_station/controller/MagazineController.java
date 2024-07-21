package cacadores.ifal.poo.book_station.controller;

import cacadores.ifal.poo.book_station.exception.MagazineNotFoundException;
import cacadores.ifal.poo.book_station.model.entity.items.Magazine;
import cacadores.ifal.poo.book_station.service.ItemService;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/magazine")
@Tag(name = "Magazine Management", description = "APIs for managing magazines")
public class MagazineController {
    @Autowired
    private ItemService itemService;

    @PostMapping()
    public ResponseEntity<Magazine> createMagazine(@RequestBody Magazine magazine) {
        return new ResponseEntity<>(itemService.addMagazine(magazine), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Magazine> getMagazineById(@PathVariable String id) {
        return new ResponseEntity<>(itemService.getMagazineById(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Magazine>> getAllMagazines() {
        return new ResponseEntity<List<Magazine>>(itemService.getMagazines(), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<Magazine> updateMagazine(@RequestBody Magazine magazine) {
        if (magazine.getId() == null)
            throw new MagazineNotFoundException("Magazine not found");
        return new ResponseEntity<>(itemService.addMagazine(magazine), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Magazine> deleteMagazine(@PathVariable String id) {
        final Magazine magazine = itemService.getMagazineById(id);

        if (magazine == null) {
            throw new MagazineNotFoundException("Magazine not found");
        }

        itemService.deleteMagazine(id);

        return new ResponseEntity<>(magazine, HttpStatus.OK);
    }
}
