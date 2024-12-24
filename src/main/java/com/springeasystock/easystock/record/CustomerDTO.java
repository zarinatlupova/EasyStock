package com.springeasystock.easystock.record;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CustomerDTO (
        Long id,
        @NotBlank(message = "Name is mandatory")
         @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
         String name,
        @NotBlank(message = "Surname is mandatory")
         @Size(min = 2, max = 50, message = "Surname must be between 2 and 50 characters")
         String surname,
        @Email(message = "Email should be valid")
         String email,
        @Size(min = 2, max = 100, message = "Address must be between 2 and 100 characters")
         String address
){

}

