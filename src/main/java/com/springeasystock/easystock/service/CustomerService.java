package com.springeasystock.easystock.service;

import com.springeasystock.easystock.record.CustomerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
public interface CustomerService {
    public CustomerDTO createCustomer(CustomerDTO customerDTO);
    CustomerDTO getCustomerById(Long customerId);

//    List<CustomerDTO> getAllCustomers();
    public Page<CustomerDTO> getAllCustomers(Pageable pageable);
    void deleteCustomer(Long customerId);
    public CustomerDTO updateCustomer(Long customerId, CustomerDTO updatedCustomer);
    public boolean existsById(Long id);

    public List<CustomerDTO> getLimitedCustomers(int limit);

}
