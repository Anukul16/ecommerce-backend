package com.ecommerce.ecommerceapp.controllers;

import java.util.List;
//import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerceapp.DTO.ProductDto;
import com.ecommerce.ecommerceapp.models.Category;
import com.ecommerce.ecommerceapp.models.Product;
import com.ecommerce.ecommerceapp.repository.CategoryRepo;
import com.ecommerce.ecommerceapp.services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@PostMapping("/create")
	public ResponseEntity<String>createProduct(@RequestBody ProductDto productDto){
		Optional<Category>ifExistCategory=categoryRepo.findById(productDto.getCategoryId());
		
		if(! ifExistCategory.isPresent()) {
			return new ResponseEntity<>("Category not found",HttpStatus.NOT_FOUND);
		}
		productService.createProduct(productDto,ifExistCategory.get());
		return new ResponseEntity<>("Product Created Succesfully",HttpStatus.CREATED);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<ProductDto>>getProductsByCategoryId(@PathVariable Long id){
		Optional<Category>ifExistCategory=categoryRepo.findById(id);
		
		if(! ifExistCategory.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<ProductDto>products=productService.getProductsByCategoryId(id);
		return new ResponseEntity<>(products,HttpStatus.OK);
	}
	
	@GetMapping("/allproducts")
	public ResponseEntity<List<ProductDto>>getAllProducts(){
		List<ProductDto>products=productService.getAllProducts();
		return new ResponseEntity<>(products,HttpStatus.OK);
	}
	
	
	@PutMapping("/update/{productId}")
	public ResponseEntity<String>updateProduct(@PathVariable Long productId,@RequestBody ProductDto productDto) throws Exception{
		Optional<Category>ifExistCategory=categoryRepo.findById(productDto.getCategoryId());
		
		if(! ifExistCategory.isPresent()) {
			return new ResponseEntity<>("Category Not Found",HttpStatus.NOT_FOUND);
		}
		productService.updateProduct(productId,productDto);
		return new ResponseEntity<>("Product Update Successfully",HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long productId) throws Exception{
		
		productService.deleteProduct(productId);
		return new ResponseEntity<>("Product Deleted Successfully",HttpStatus.OK);
	}
	
	

}