package com.ecommerce.ecommerceapp.repository;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerceapp.models.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
	
}
