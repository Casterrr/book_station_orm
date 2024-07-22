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

import cacadores.ifal.poo.book_station.dto.Tenant.TenantCreateDTO;
import cacadores.ifal.poo.book_station.dto.Tenant.TenantResponseDTO;
import cacadores.ifal.poo.book_station.dto.Tenant.TenantUpdateDTO;
import cacadores.ifal.poo.book_station.service.TenantService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/tenants")
@Tag(name = "Tenant", description = "APIs para gerenciar locatários")
public class TenantController {
    @Autowired
    private TenantService tenantService;

    @PostMapping()
    public ResponseEntity<TenantResponseDTO> createTenant(@RequestBody TenantCreateDTO tenantCreateDTO) {
        return new ResponseEntity<>(tenantService.createTenant(tenantCreateDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TenantResponseDTO> getTenantById(@PathVariable String id) {
        return new ResponseEntity<>(tenantService.getTenantById(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<TenantResponseDTO>> getAllTenants() {
        return new ResponseEntity<>(tenantService.getAllTenants(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TenantResponseDTO> updateTenant(@PathVariable String id, @RequestBody TenantUpdateDTO tenantUpdateDTO) {
        return new ResponseEntity<>(tenantService.updateTenant(id, tenantUpdateDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTenant(@PathVariable String id) {
        tenantService.deleteTenant(id);
        return ResponseEntity.noContent().build();
    }
}