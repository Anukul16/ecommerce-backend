package com.ecommerce.ecommerceapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerceapp.models.User;


@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	
	User findByEmail(String email);
}
