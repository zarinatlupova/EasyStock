package com.springeasystock.easystock.service;

import com.springeasystock.easystock.record.ItemDTO;
import com.springeasystock.easystock.record.RoleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoleService {
    RoleDTO createRole(RoleDTO roleDTO);
    Page<RoleDTO> getAllRoles(Pageable pageable);


    RoleDTO getRoleById(Long roleId);

    RoleDTO updateRole(Long roleId, RoleDTO updatedItem);

    void deleteRole(Long roleId);
    public boolean existsById(Long id);

}
