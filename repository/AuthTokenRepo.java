package com.ecommerce.ecommerceapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerceapp.models.AuthToken;
import com.ecommerce.ecommerceapp.models.User;

@Repository
public interface AuthTokenRepo extends JpaRepository<AuthToken, Long>{
	
	AuthToken findByUser(User user);
	
	AuthToken findByToken(String token);
}
