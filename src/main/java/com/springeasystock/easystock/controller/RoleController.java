package com.springeasystock.easystock.controller;

import com.springeasystock.easystock.record.RoleDTO;
import com.springeasystock.easystock.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private RoleService roleService;
    //TODO user must be , like admin
    // должны быть общие пользователи (таблица users)
    // employee содержит ключ на обьект в таблице юзеров
    // то есть они все представляют собой пользователей с ролью


    @PostMapping
    public ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO roleDTO){
        RoleDTO savedRole = roleService.createRole(roleDTO);
        return new ResponseEntity<>(savedRole, HttpStatus.CREATED);

    }
//    @GetMapping
//    public ResponseEntity<List<RoleDTO>> getAllRoles(){
//        List<RoleDTO> roles = roleService.getAllRoles();
//        return ResponseEntity.ok(roles);
//    }

    @GetMapping
    public ResponseEntity<List<RoleDTO>> getAllRoles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);

        // Получаем пагинированный список из сервиса
        Page<RoleDTO> rolesPage = roleService.getAllRoles(pageable);

        // Получаем общее количество записей
        long totalElements = rolesPage.getTotalElements();

        // Создаем HTTP-заголовок с общим количеством записей
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(totalElements));

        // Возвращаем пагинированный список и заголовки
        return ResponseEntity.ok()
                .headers(headers)
                .body(rolesPage.getContent());
    }

    @GetMapping("{id}")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable("id") Long roleId){
        RoleDTO roleDTO = roleService.getRoleById(roleId);
        return ResponseEntity.ok(roleDTO);

    }

    @PutMapping("{id}")
    public ResponseEntity<RoleDTO> updateRole(@PathVariable("id")Long roleId,
                                                       @RequestBody RoleDTO updatedRole){
        RoleDTO roleDTO = roleService.updateRole(roleId, updatedRole);
        return ResponseEntity.ok(roleDTO);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteRole(@PathVariable("id")Long roleId){
        roleService.deleteRole(roleId);
        return ResponseEntity.ok("Deleted Successfully!");

    }

}
