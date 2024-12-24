package com.springeasystock.easystock.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.springeasystock.easystock.record.RoleDTO;
import com.springeasystock.easystock.model.Role;
@Mapper
public interface RoleMapperInterface {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleDTO toDTO(Role role);
    Role toEntity(RoleDTO roleDTO);
}
