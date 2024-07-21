package cacadores.ifal.poo.book_station.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cacadores.ifal.poo.book_station.dto.Tenant.TenantCreateDTO;
import cacadores.ifal.poo.book_station.dto.Tenant.TenantResponseDTO;
import cacadores.ifal.poo.book_station.dto.Tenant.TenantUpdateDTO;
import cacadores.ifal.poo.book_station.exception.TenantAlreadyExistsException;
import cacadores.ifal.poo.book_station.exception.TenantNotFoundException;
import cacadores.ifal.poo.book_station.exception.TenantValidationException;
import cacadores.ifal.poo.book_station.model.entity.Tenant;
import cacadores.ifal.poo.book_station.repository.TenantRepository;


@Service
public class TenantService {

    @Autowired
    private TenantRepository tenantRepository;

    public TenantResponseDTO createTenant(TenantCreateDTO tenantCreateDTO) {
        validateTenantCreateDTO(tenantCreateDTO);

        if (tenantRepository.existsByEmail(tenantCreateDTO.getEmail())) {
            throw new TenantAlreadyExistsException("Um locatário com o email " + tenantCreateDTO.getEmail() + " já existe.");
        }
        if (tenantRepository.existsByCpf(tenantCreateDTO.getCpf())) {
            throw new TenantAlreadyExistsException("Um locatário com o CPF " + tenantCreateDTO.getCpf() + " já existe.");
        }
        Tenant tenant = new Tenant();
        BeanUtils.copyProperties(tenantCreateDTO, tenant);
        tenant = tenantRepository.save(tenant);
        return convertToTenantResponseDTO(tenant);
    }

    public TenantResponseDTO updateTenant(String id, TenantUpdateDTO tenantUpdateDTO) {
        validateTenantUpdateDTO(tenantUpdateDTO);

        Tenant existingTenant = findTenantEntityById(id);
        BeanUtils.copyProperties(tenantUpdateDTO, existingTenant);
        existingTenant = tenantRepository.save(existingTenant);
        return convertToTenantResponseDTO(existingTenant);
    }

    public TenantResponseDTO getTenantById(String id) {
        Tenant tenant = findTenantEntityById(id);
        return convertToTenantResponseDTO(tenant);
    }

    public List<TenantResponseDTO> getAllTenants() {
        return tenantRepository.findAll().stream()
                .map(this::convertToTenantResponseDTO)
                .collect(Collectors.toList());
    }

    public void deleteTenant(String id) {
        tenantRepository.deleteById(id);
    }

    private Tenant findTenantEntityById(String id) {
        return tenantRepository.findById(id)
                .orElseThrow(() -> new TenantNotFoundException("Locatário com ID " + id + " não encontrado."));
    }

    private void validateTenantCreateDTO(TenantCreateDTO dto) {
        if (isNullOrEmpty(dto.getName())) {
            throw new TenantValidationException("O nome não pode ser vazio.");
        }
        if (isNullOrEmpty(dto.getEmail())) {
            throw new TenantValidationException("O email não pode ser vazio.");
        }
        if (isNullOrEmpty(dto.getPassword())) {
            throw new TenantValidationException("A senha não pode ser vazia.");
        }
        if (isNullOrEmpty(dto.getAddress())) {
            throw new TenantValidationException("O endereço não pode ser vazio.");
        }
        if (isNullOrEmpty(dto.getCpf())) {
            throw new TenantValidationException("O CPF não pode ser vazio.");
        }
        if (isNullOrEmpty(dto.getPhone())) {
            throw new TenantValidationException("O telefone não pode ser vazio.");
        }
    }

    private void validateTenantUpdateDTO(TenantUpdateDTO dto) {
        if (dto.getName() != null && dto.getName().trim().isEmpty()) {
            throw new TenantValidationException("O nome não pode ser vazio.");
        }
        if (dto.getEmail() != null && dto.getEmail().trim().isEmpty()) {
            throw new TenantValidationException("O email não pode ser vazio.");
        }
        if (dto.getAddress() != null && dto.getAddress().trim().isEmpty()) {
            throw new TenantValidationException("O endereço não pode ser vazio.");
        }
        if (dto.getPhone() != null && dto.getPhone().trim().isEmpty()) {
            throw new TenantValidationException("O telefone não pode ser vazio.");
        }
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    private TenantResponseDTO convertToTenantResponseDTO(Tenant tenant) {
        TenantResponseDTO dto = new TenantResponseDTO();
        BeanUtils.copyProperties(tenant, dto);
        return dto;
    }
}