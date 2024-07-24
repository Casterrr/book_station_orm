package cacadores.ifal.poo.book_station.controller;

import cacadores.ifal.poo.book_station.dto.Magazine.MagazineCreateDTO;
import cacadores.ifal.poo.book_station.dto.Magazine.MagazineResponseDTO;
import cacadores.ifal.poo.book_station.dto.Magazine.MagazineUpdateDTO;
import cacadores.ifal.poo.book_station.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MagazineControllerTest {

    @Mock
    private ItemService itemService;

    @InjectMocks
    private MagazineController magazineController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateMagazine() {
        MagazineCreateDTO magazineCreateDTO = new MagazineCreateDTO();
        MagazineResponseDTO magazineResponseDTO = new MagazineResponseDTO();
        when(itemService.addMagazine(magazineCreateDTO)).thenReturn(magazineResponseDTO);

        ResponseEntity<MagazineResponseDTO> response = magazineController.createMagazine(magazineCreateDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(magazineResponseDTO, response.getBody());
        verify(itemService).addMagazine(magazineCreateDTO);
    }

    @Test
    void testGetMagazineById() {
        String id = "1";
        MagazineResponseDTO magazineResponseDTO = new MagazineResponseDTO();
        when(itemService.getMagazineById(id)).thenReturn(magazineResponseDTO);

        ResponseEntity<MagazineResponseDTO> response = magazineController.getMagazineById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(magazineResponseDTO, response.getBody());
        verify(itemService).getMagazineById(id);
    }

    @Test
    void testGetAllMagazines() {
        List<MagazineResponseDTO> magazines = Arrays.asList(new MagazineResponseDTO(), new MagazineResponseDTO());
        when(itemService.getMagazines()).thenReturn(magazines);

        ResponseEntity<List<MagazineResponseDTO>> response = magazineController.getAllMagazines();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(magazines, response.getBody());
        assertEquals(2, response.getBody().size());
        verify(itemService).getMagazines();
    }

    @Test
    void testUpdateMagazine() {
        String id = "1";
        MagazineUpdateDTO magazineUpdateDTO = new MagazineUpdateDTO();
        MagazineResponseDTO magazineResponseDTO = new MagazineResponseDTO();
        when(itemService.updateMagazine(id, magazineUpdateDTO)).thenReturn(magazineResponseDTO);

        ResponseEntity<MagazineResponseDTO> response = magazineController.updateMagazine(id, magazineUpdateDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(magazineResponseDTO, response.getBody());
        verify(itemService).updateMagazine(id, magazineUpdateDTO);
    }

    @Test
    void testDeleteMagazine() {
        String id = "1";
        doNothing().when(itemService).deleteMagazine(id);

        ResponseEntity<Void> response = magazineController.deleteMagazine(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(itemService).deleteMagazine(id);
    }
}