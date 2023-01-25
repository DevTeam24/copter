package com.compter.copter.service;

import com.compter.copter.domain.Role;
import com.compter.copter.model.RoleDTO;
import com.compter.copter.repos.RoleRepository;
import com.compter.copter.util.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(final RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<RoleDTO> findAll() {
        final List<Role> roles = roleRepository.findAll(Sort.by("roleId"));
        return roles.stream()
                .map((role) -> mapToDTO(role, new RoleDTO()))
                .collect(Collectors.toList());
    }

    public RoleDTO get(final Long roleId) {
        return roleRepository.findById(roleId)
                .map(role -> mapToDTO(role, new RoleDTO()))
                .orElseThrow(() -> new NotFoundException());
    }

    public Long create(final RoleDTO roleDTO) {
        final Role role = new Role();
        mapToEntity(roleDTO, role);
        return roleRepository.save(role).getRoleId();
    }

    public void update(final Long roleId, final RoleDTO roleDTO) {
        final Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new NotFoundException());
        mapToEntity(roleDTO, role);
        roleRepository.save(role);
    }

    public void delete(final Long roleId) {
        roleRepository.deleteById(roleId);
    }

    private RoleDTO mapToDTO(final Role role, final RoleDTO roleDTO) {
        roleDTO.setRoleId(role.getRoleId());
        roleDTO.setName(role.getName());
        return roleDTO;
    }

    private Role mapToEntity(final RoleDTO roleDTO, final Role role) {
        role.setName(roleDTO.getName());
        return role;
    }

}
