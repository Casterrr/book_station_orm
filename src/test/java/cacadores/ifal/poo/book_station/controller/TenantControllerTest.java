package cacadores.ifal.poo.book_station.controller;

import cacadores.ifal.poo.book_station.dto.Tenant.TenantCreateDTO;
import cacadores.ifal.poo.book_station.dto.Tenant.TenantResponseDTO;
import cacadores.ifal.poo.book_station.dto.Tenant.TenantUpdateDTO;
import cacadores.ifal.poo.book_station.service.TenantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TenantControllerTest {

    @Mock
    private TenantService tenantService;

    @InjectMocks
    private TenantController tenantController;

    private TenantCreateDTO tenantCreateDTO;
    private TenantUpdateDTO tenantUpdateDTO;
    private TenantResponseDTO tenantResponseDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        tenantCreateDTO = new TenantCreateDTO();
        tenantUpdateDTO = new TenantUpdateDTO();
        tenantResponseDTO = new TenantResponseDTO();
    }

    @Test
    void createTenant() {
        when(tenantService.createTenant(tenantCreateDTO)).thenReturn(tenantResponseDTO);

        ResponseEntity<TenantResponseDTO> response = tenantController.createTenant(tenantCreateDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(tenantResponseDTO, response.getBody());
        verify(tenantService).createTenant(tenantCreateDTO);
    }

    @Test
    void getTenantById() {
        String id = "1";
        when(tenantService.getTenantById(id)).thenReturn(tenantResponseDTO);

        ResponseEntity<TenantResponseDTO> response = tenantController.getTenantById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tenantResponseDTO, response.getBody());
        verify(tenantService).getTenantById(id);
    }

    @Test
    void getAllTenants() {
        List<TenantResponseDTO> tenants = Arrays.asList(tenantResponseDTO);
        when(tenantService.getAllTenants()).thenReturn(tenants);

        ResponseEntity<List<TenantResponseDTO>> response = tenantController.getAllTenants();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tenants, response.getBody());
        verify(tenantService).getAllTenants();
    }

    @Test
    void updateTenant() {
        String id = "1";
        when(tenantService.updateTenant(id, tenantUpdateDTO)).thenReturn(tenantResponseDTO);

        ResponseEntity<TenantResponseDTO> response = tenantController.updateTenant(id, tenantUpdateDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tenantResponseDTO, response.getBody());
        verify(tenantService).updateTenant(id, tenantUpdateDTO);
    }

    @Test
    void deleteTenant() {
        String id = "1";

        ResponseEntity<Void> response = tenantController.deleteTenant(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(tenantService).deleteTenant(id);
    }
}