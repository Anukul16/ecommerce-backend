package com.ecommerce.ecommerceapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecommerce.ecommerceapp.models.WishList;
import com.ecommerce.ecommerceapp.repository.WishListRepo;

@Service
public class WishListService {
	
	@Autowired
    private WishListRepo wishListRepo;

  

    public void addToWishList(WishList wishList) {
        wishListRepo.save(wishList);
    }

    public boolean isProductInWishList(Long userId, Long productId) {
        // Check if the product is already in the user's wishlist
        return wishListRepo.existsByUserIdAndProductId(userId, productId);
    }
}

