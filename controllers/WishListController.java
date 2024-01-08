package com.ecommerce.ecommerceapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerceapp.models.Product;
import com.ecommerce.ecommerceapp.models.User;
import com.ecommerce.ecommerceapp.models.WishList;
import com.ecommerce.ecommerceapp.services.AuthTokenService;
import com.ecommerce.ecommerceapp.services.ProductService;
import com.ecommerce.ecommerceapp.services.WishListService;

@RestController
@RequestMapping("/wishlist")
public class WishListController {

    @Autowired
    private AuthTokenService authTokenService;

    @Autowired
    private ProductService productService; // Assuming you have ProductService

    @Autowired
    private WishListService wishListService;

    @PostMapping("/add/{token}")
    public ResponseEntity<String> addToWishList(@RequestBody Product product, @PathVariable String token) {
        try {
            // Authenticate the token and get the user
            User user = authTokenService.getUserByToken(token);

            // Ensure the product is valid (exists in the database)
            Product existingProduct = productService.getProductById(product.getProductId());
            if (existingProduct==null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid product ID.");
            }

            // Check if the product is already in the user's wishlist
            if (wishListService.isProductInWishList(user.getUserId(), existingProduct.getProductId())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product is already in the wishlist.");
            }

            // Add the product to the user's wishlist
            WishList wishList = new WishList(user, existingProduct);
            wishListService.addToWishList(wishList);

            return ResponseEntity.status(HttpStatus.CREATED).body("Product added to wishlist successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token.");
        }
    }
}
