package com.ecommerce.ecommerceapp.controllers;

import java.util.List;

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

import com.ecommerce.ecommerceapp.models.Category;
import com.ecommerce.ecommerceapp.services.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	
	@PostMapping("/create")
	private long createCategory(@RequestBody Category category) {
		Category savedCategory=categoryService.createCategory(category);
		return savedCategory.getId();
	}
	
	@GetMapping("/no_{id}")
	private Category getCategory(@PathVariable Long id) {
		return categoryService.getCategory(id);
	}
	
	@GetMapping("/list")
	private List<Category>getCategoryList(){
		return categoryService.getCategoryList();
	}
	
	
	
	@PutMapping("/update/{id}")
	private ResponseEntity<String>updateCategory(@PathVariable Long id, @RequestBody Category updatedCategory){
		
		String response=categoryService.updateCategory(id, updatedCategory);
		
		if("Updated Successfully".equals(response)) {
			return ResponseEntity.ok(response);
		}else if("Category Not Found".equals(response)) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	
	@DeleteMapping("/delete/{id}")
	private ResponseEntity<String>deleteCategory(@PathVariable Long id){
		
		String response=categoryService.deleteCategory(id);
		if("Category Deleted Successfully".equals(response)) {
			return ResponseEntity.ok(response);
		}else if("Category Not Found".equals(response)) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	 
}
