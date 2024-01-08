package com.ecommerce.ecommerceapp.repository;

//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerceapp.models.WishList;

@Repository
public interface WishListRepo extends JpaRepository<WishList, Long>{
	
	@Query("SELECT CASE WHEN COUNT(w) > 0 THEN true ELSE false END FROM WishList w WHERE w.user.id = :userId AND w.product.id = :productId")
    boolean existsByUsersContainingAndProductsContaining(@Param("userId") Long userId, @Param("productId") Long productId);


}
