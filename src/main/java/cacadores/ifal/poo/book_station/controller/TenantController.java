package cacadores.ifal.poo.book_station.controller;

import cacadores.ifal.poo.book_station.model.entity.Tenant;
import cacadores.ifal.poo.book_station.service.TenantService;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tenants")
@Tag(name = "Tenant", description = "API for managing tenants")
public class TenantController {

    @Autowired
    private TenantService tenantService;

    @GetMapping
    public List<Tenant> getAllTenants() {
        return tenantService.getAllTenants();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tenant> getTenantById(@PathVariable String id) {
        Tenant tenant = tenantService.getTenantById(id);
        return ResponseEntity.ok(tenant);
    }

    @PostMapping
    public ResponseEntity<Tenant> createTenant(@RequestBody Tenant tenant) {
        Tenant newTenant = tenantService.createTenant(tenant);
        return ResponseEntity.ok(newTenant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tenant> updateTenant(@PathVariable String id, @RequestBody Tenant tenantDetails) {
        Tenant updatedTenant = tenantService.updateTenant(id, tenantDetails);
        return ResponseEntity.ok(updatedTenant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTenant(@PathVariable String id) {
        tenantService.deleteTenant(id);
        return ResponseEntity.ok().build();
    }
}