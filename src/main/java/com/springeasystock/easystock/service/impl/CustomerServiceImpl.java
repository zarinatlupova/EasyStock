package com.springeasystock.easystock.service.impl;

import com.springeasystock.easystock.exception.CustomNotFoundException;
import com.springeasystock.easystock.record.CustomerDTO;
import com.springeasystock.easystock.mapper.CustomerMapper;
import com.springeasystock.easystock.model.Customer;
import com.springeasystock.easystock.repo.CustomerRepository;
import com.springeasystock.easystock.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.toEntity(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return CustomerMapper.toDTO(savedCustomer);
    }

    @Override
    public CustomerDTO getCustomerById(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                        .orElseThrow(() -> new CustomNotFoundException(customerId));

        return CustomerMapper.toDTO(customer);

    }



    @Override
    public Page<CustomerDTO> getAllCustomers(Pageable pageable) {
        Page<Customer> customers = customerRepository.findAll(pageable);
        return customers.map(CustomerMapper::toDTO);
    }


    @Override
    public CustomerDTO updateCustomer(Long customerId, CustomerDTO updatedCustomer) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(
                        NoSuchElementException::new
        );
        customer.setName(updatedCustomer.name());
        customer.setSurname(updatedCustomer.surname());
        customer.setEmail(updatedCustomer.email());
        customer.setAddress(updatedCustomer.address());
        Customer updatedCustomerObj =  customerRepository.save(customer);
        return CustomerMapper.toDTO(updatedCustomerObj); //TODO: entitiy
    }

    @Override
    public void deleteCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId).
                orElseThrow(NoSuchElementException::new
        );
        customerRepository.deleteById(customer.getId());
    }

    @Override
    public boolean existsById(Long id) {
        return customerRepository.existsById(id);
    }

    @Override
    public List<CustomerDTO> getLimitedCustomers(int limit) {
        List<Customer> customers = customerRepository.findAll(); // Fetch all
        return customers.stream()
                .limit(limit) // Apply the limit
                .map(CustomerMapper::toDTO)
                .collect(Collectors.toList());
    }
}
