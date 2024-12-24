package com.springeasystock.easystock.service.impl;

import com.springeasystock.easystock.exception.CustomNotFoundException;
import com.springeasystock.easystock.record.EmployeeDTO;
import com.springeasystock.easystock.mapper.EmployeeMapper;
import com.springeasystock.easystock.model.*;
import com.springeasystock.easystock.repo.EmployeeRepository;
import com.springeasystock.easystock.repo.RoleRepository;
import com.springeasystock.easystock.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = EmployeeMapper.toEntity(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.toDTO(savedEmployee);
    }

    @Override
    public Page<EmployeeDTO> getAllEmployee(Pageable pageable) {
        Page<Employee> employees = employeeRepository.findAll(pageable);
        return employees.map(EmployeeMapper::toDTO);
    }

    @Override
    public Employee assignItemToOrderList(Long employeeId, Long roleId) {
        Set<Role> roles = null;
        Employee employee = employeeRepository.findById(employeeId).get();
        Role role = roleRepository.findById(roleId).get();
        roles = employee.getRoles();
        roles.add(role);
        employee.setRoles(roles);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee unassignRoleFromEmployee(Long employeeId, Long roleId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("OrderList not found with id: " + employeeId));
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("Item not found with id: " + roleId));

        Set<Role> roleSet = employee.getRoles();
        if (roleSet.contains(role)) {
            roleSet.remove(role);
            employee.setRoles(roleSet);
            return employeeRepository.save(employee);
        } else {
            throw new IllegalArgumentException("Item with id " + roleId + " is not assigned to OrderList with id " + employeeId);
        }
    }


    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new CustomNotFoundException(employeeId));
        return EmployeeMapper.toDTO(employee);

    }

    @Override
    public EmployeeDTO updateEmployee(Long employeeId, EmployeeDTO updatedEmployee) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new CustomNotFoundException(employeeId));
        employee.setUsername(updatedEmployee.username());
        employee.setRole(updatedEmployee.role());
        employee.setCreatedAt(updatedEmployee.createdAt());
        employee.setZone(updatedEmployee.zoneId());
        Employee updatedEmployeeObj =  employeeRepository.save(employee);
        return EmployeeMapper.toDTO(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new CustomNotFoundException(employeeId));
        employeeRepository.deleteById(employee.getId());
    }

    @Override
    public boolean existsById(Long id) {
        return employeeRepository.existsById(id);
    }
}
