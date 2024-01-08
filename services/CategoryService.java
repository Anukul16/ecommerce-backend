package com.ecommerce.ecommerceapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerceapp.models.Category;
import com.ecommerce.ecommerceapp.repository.CategoryRepo;


@Service
public class CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	
	
	
	public Category createCategory(Category category) {
		return categoryRepo.save(category);
	}
	
	
	
	public Category getCategory(long id) {
		return categoryRepo.findById(id).get();
	}
	
	
	
	public List<Category> getCategoryList(){
		return categoryRepo.findAll();
	}
	
	
	
	public String updateCategory(Long id,Category updatedCategory) {
		Optional<Category>ifExistCategory=categoryRepo.findById(id);
		
		if(ifExistCategory.isPresent()) {
			// Getting the existing category if id present
			Category existingCategory=ifExistCategory.get();
			
			// updating the category
			existingCategory.setTitle(updatedCategory.getTitle());
			existingCategory.setDescription(updatedCategory.getDescription());
			existingCategory.setImgURL(updatedCategory.getImgURL());
			
			// update done now saving
			
			categoryRepo.save(existingCategory);
			
			return"Updated Successfully";
			
			
		}else {
			return "Category Not Found";
		}
	}
	
	
	
	public String deleteCategory(Long id) {
		
		Optional<Category>ifExistCategory=categoryRepo.findById(id);
		
		if(ifExistCategory.isPresent()) {
			categoryRepo.deleteById(id);
			return "Category Deleted Successfully";
		}else {
			return "Category Not Found";
		}
	}
}
