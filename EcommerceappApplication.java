package com.ecommerce.ecommerceapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@ComponentScan(basePackages = {
        "com.ecommerce.ecommerceapp.services",
        "com.ecommerce.ecommerceapp.controllers",
        "com.ecommerce.ecommerceapp.models",
        "com.ecommerce.ecommerceapp.repository",
        "com.ecommerce.ecommerceapp.configuration"
})
public class EcommerceappApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceappApplication.class, args);
    }
}
