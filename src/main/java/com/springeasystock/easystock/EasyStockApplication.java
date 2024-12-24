package com.springeasystock.easystock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.springeasystock.easystock.model")
@EnableJpaRepositories("com.springeasystock.easystock.repo")
public class EasyStockApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyStockApplication.class, args);
    }

}
