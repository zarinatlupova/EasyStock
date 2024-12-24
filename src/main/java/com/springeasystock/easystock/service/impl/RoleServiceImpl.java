package com.springeasystock.easystock.service.impl;

import com.springeasystock.easystock.exception.CustomNotFoundException;
import com.springeasystock.easystock.mapper.ItemMapper;
import com.springeasystock.easystock.model.Item;
import com.springeasystock.easystock.record.ItemDTO;
import com.springeasystock.easystock.record.RoleDTO;
import com.springeasystock.easystock.mapper.RoleMapper;
import com.springeasystock.easystock.model.Role;
import com.springeasystock.easystock.repo.RoleRepository;
import com.springeasystock.easystock.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;


    @Override
    public RoleDTO createRole(RoleDTO roleDTO) {
        Role role = RoleMapper.toEntity(roleDTO);
        Role savedRole = roleRepository.save(role);
        return RoleMapper.toDTO(savedRole);
    }

    @Override
    public Page<RoleDTO> getAllRoles(Pageable pageable) {
        Page<Role> roles = roleRepository.findAll(pageable);
        return roles.map(RoleMapper::toDTO);
    }
    @Override
    public RoleDTO getRoleById(Long roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new CustomNotFoundException(roleId));
        return RoleMapper.toDTO(role);

    }

    @Override
    public RoleDTO updateRole(Long roleId, RoleDTO updatedRole) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new CustomNotFoundException(roleId));
        role.setName(updatedRole.name());
        Role updatedObj =  roleRepository.save(role);
        return RoleMapper.toDTO(updatedObj);
    }

    @Override
    public void deleteRole(Long roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new CustomNotFoundException(roleId));
        roleRepository.deleteById(role.getId());
    }

    @Override
    public boolean existsById(Long id) {
        return roleRepository.existsById(id);
    }
}
