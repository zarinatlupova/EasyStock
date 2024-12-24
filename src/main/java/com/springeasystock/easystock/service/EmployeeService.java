package com.springeasystock.easystock.service;

import com.springeasystock.easystock.record.EmployeeDTO;
import com.springeasystock.easystock.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);
//    List<EmployeeDTO> getAllEmployee();
    Page<EmployeeDTO> getAllEmployee(Pageable pageable);

    Employee assignItemToOrderList(Long employeeId, Long roleId);

    Employee unassignRoleFromEmployee(Long employeeId, Long roleId);

    EmployeeDTO getEmployeeById(Long employeeId);

    EmployeeDTO updateEmployee(Long employeeId, EmployeeDTO updatedEmployee);


    void deleteEmployee(Long employeeId);
    public boolean existsById(Long id);

}
