package com.springeasystock.easystock;
import com.springeasystock.easystock.exception.CustomNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.EnabledIf;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;


import com.springeasystock.easystock.service.impl.CustomerServiceImpl;
import com.springeasystock.easystock.record.CustomerDTO;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerServiceIntegrationTest {

    // Start a PostgreSQL container for testing
    private static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>(DockerImageName.parse("postgres:13"))
            .withDatabaseName("easystock")
            .withUsername("postgres")
            .withPassword("user");

    @Autowired
    private CustomerServiceImpl customerService;

    @BeforeAll
    public static void setUp() {
        // Start the PostgreSQL container before running tests
        postgresContainer.start();

        // Optionally, you can print out the connection details to verify:
        System.out.println("PostgreSQL container started:");
        System.out.println("URL: " + postgresContainer.getJdbcUrl());
        System.out.println("Username: " + postgresContainer.getUsername());
        System.out.println("Password: " + postgresContainer.getPassword());
    }

    @AfterAll
    public static void tearDown() {
        // Stop the container after tests are completed
        postgresContainer.stop();
    }

    @Test
    public void testCreateCustomer() {
        // Create a CustomerDTO to be used for the test
        CustomerDTO customerDTO = new CustomerDTO(1L,"Jan", "Doe", "john.doe@example.com", "123 Main St");

        // Use the service to create a customer
        CustomerDTO createdCustomer = customerService.createCustomer(customerDTO);

        // Assertions to verify the customer was created correctly
        assertNotNull(createdCustomer);
        assertEquals(customerDTO.name(), createdCustomer.name());
        assertEquals(customerDTO.surname(), createdCustomer.surname());
        assertEquals(customerDTO.email(), createdCustomer.email());
    }

    @Test
    public void testGetCustomerById() {
        // Create a new customer
        CustomerDTO customerDTO = new CustomerDTO(1L,"Jane", "Doe", "jane.doe@example.com", "456 Elm St");
        CustomerDTO createdCustomer = customerService.createCustomer(customerDTO);

        // Fetch the customer by its ID
        CustomerDTO fetchedCustomer = customerService.getCustomerById(createdCustomer.id());

        // Assertions to verify the fetched customer is correct
        assertNotNull(fetchedCustomer);
        assertEquals(createdCustomer.id(), fetchedCustomer.id());
    }

    @Test
    public void testDeleteCustomer() {
        // Create a new customer
        CustomerDTO customerDTO = new CustomerDTO(19L,"Tom", "Smith", "tom.smith@example.com", "789 Pine St");
        CustomerDTO createdCustomer = customerService.createCustomer(customerDTO);

        // Delete the customer by ID
        customerService.deleteCustomer(createdCustomer.id());

        // Verify that the customer is deleted (attempting to fetch will throw an exception)
        assertThrows(CustomNotFoundException.class, () -> customerService.getCustomerById(createdCustomer.id()));
    }

    @Test
    public void testUpdateCustomer() {

        // Create a new customer
        CustomerDTO customerDTO = new CustomerDTO(1L,"Alice", "Johnson", "alice.johnson@example.com", "101 Maple St");
        CustomerDTO createdCustomer = customerService.createCustomer(customerDTO);

        // Create an updated DTO
        CustomerDTO updatedCustomer = new CustomerDTO(1L,"Alice", "Smith", "alice.smith@example.com", "101 Maple St");

        // Update the customer
        CustomerDTO updatedCustomerResult = customerService.updateCustomer(createdCustomer.id(), updatedCustomer);

        // Assertions to verify the update was successful
        assertNotNull(updatedCustomerResult);
        assertEquals(updatedCustomer.name(), updatedCustomerResult.name());
        assertEquals(updatedCustomer.surname(), updatedCustomerResult.surname());
        assertEquals(updatedCustomer.email(), updatedCustomerResult.email());
    }
}

