package cacadores.ifal.poo.book_station.service;

import cacadores.ifal.poo.book_station.model.entity.Tenant;
import cacadores.ifal.poo.book_station.repository.TenantRepository;
import cacadores.ifal.poo.book_station.exception.TenantNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TenantService {

    @Autowired
    private TenantRepository tenantRepository;

    public List<Tenant> getAllTenants() {
        return tenantRepository.findAll();
    }

    public Tenant getTenantById(String id) {
        return tenantRepository.findById(id)
                .orElseThrow(() -> new TenantNotFoundException("Tenant not found with id: " + id));
    }

    public Tenant createTenant(Tenant tenant) {
        return tenantRepository.save(tenant);
    }

    public Tenant updateTenant(String id, Tenant tenantDetails) {
        Tenant tenant = getTenantById(id);
        tenant.setAddress(tenantDetails.getAddress());
        tenant.setCpf(tenantDetails.getCpf());
        tenant.setPhone(tenantDetails.getPhone());
        return tenantRepository.save(tenant);
    }

    public void deleteTenant(String id) {
        Tenant tenant = getTenantById(id);
        tenantRepository.delete(tenant);
    }
}