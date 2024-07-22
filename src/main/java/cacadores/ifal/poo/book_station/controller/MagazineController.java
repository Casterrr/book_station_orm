package cacadores.ifal.poo.book_station.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import cacadores.ifal.poo.book_station.dto.Magazine.MagazineCreateDTO;
import cacadores.ifal.poo.book_station.dto.Magazine.MagazineResponseDTO;
import cacadores.ifal.poo.book_station.dto.Magazine.MagazineUpdateDTO;
import cacadores.ifal.poo.book_station.service.ItemService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/magazine")
@Tag(name = "Magazine", description = "APIs for managing magazines")
public class MagazineController {
    @Autowired
    private ItemService itemService;

    @PostMapping()
    public ResponseEntity<MagazineResponseDTO> createMagazine(@RequestBody MagazineCreateDTO magazineCreateDTO) {
        return new ResponseEntity<>(itemService.addMagazine(magazineCreateDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MagazineResponseDTO> getMagazineById(@PathVariable String id) {
        return new ResponseEntity<>(itemService.getMagazineById(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<MagazineResponseDTO>> getAllMagazines() {
        return new ResponseEntity<>(itemService.getMagazines(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MagazineResponseDTO> updateMagazine(@PathVariable String id, @RequestBody MagazineUpdateDTO magazineUpdateDTO) {
        return new ResponseEntity<>(itemService.updateMagazine(id, magazineUpdateDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMagazine(@PathVariable String id) {
        itemService.deleteMagazine(id);
        return ResponseEntity.noContent().build();
    }

    
}
