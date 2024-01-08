package com.ecommerce.ecommerceapp.services;

import java.util.*;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerceapp.DTO.ProductDto;
import com.ecommerce.ecommerceapp.models.Category;
import com.ecommerce.ecommerceapp.models.Product;
import com.ecommerce.ecommerceapp.repository.ProductRepo;

@Service
public class ProductService {

	@Autowired
	private ProductRepo productRepo;
	
	

	public void createProduct(ProductDto productDto, Category category) {
		
		Product product=new Product();
		
		product.setProductName(productDto.getProductName());
		product.setProductImgURL(productDto.getProductImgURL());
		product.setProductPrice(productDto.getProductPrice());
		product.setProductDescription(productDto.getProductDescription());
		product.setCategory(category);
		
		productRepo.save(product);
		
	}
	
	public Product getProductById(Long id) throws Exception {
		Optional<Product>ifProductExist=productRepo.findById(id);
		
		if(! ifProductExist.isPresent()) {
			throw new Exception("Product Not present");
		}
		Product product=ifProductExist.get();
		
		return product;
	}
	
	public ProductDto productToDto(Product product) {
		ProductDto productDto=new ProductDto();
		
		productDto.setProductId(product.getProductId());
		productDto.setProductName(product.getProductName());
		productDto.setProductImgURL(product.getProductImgURL());
		productDto.setProductDescription(product.getProductDescription());
		productDto.setProductPrice(product.getProductPrice());
		productDto.setCategoryId(product.getCategory().getId());
		
		return productDto;
	}
	
	
	public List<ProductDto>getProductsByCategoryId(Long id){
		List<Product>allProducts=productRepo.findAll();
		List<ProductDto>response=new ArrayList<ProductDto>();
		for(Product product:allProducts) {
			if(product.getCategory().getId().equals(id)) {
				response.add(productToDto(product));
			}
		}
		return response;
	}
	
	
	public List<ProductDto>getAllProducts(){
		List<Product>allProducts=productRepo.findAll();
		List<ProductDto>response=new ArrayList<>();
		for(Product product:allProducts) {
			response.add(productToDto(product));
		}
		return response;
	}
	
	
	
	public void updateProduct(Long productId,ProductDto productDto) throws Exception {
		Optional<Product>ifProductExist=productRepo.findById(productId);
		
		if(! ifProductExist.isPresent()) {
			throw new Exception("Product Not present");
		}
		Product product=ifProductExist.get();
		
		product.setProductName(productDto.getProductName());
		product.setProductImgURL(productDto.getProductImgURL());
		product.setProductPrice(productDto.getProductPrice());
		product.setProductDescription(productDto.getProductDescription());
		
		productRepo.save(product);
	}
	
	
	public void deleteProduct(Long productId) throws Exception {
		Optional<Product>ifProductExist=productRepo.findById(productId);
		
		if(! ifProductExist.isPresent()) {
			throw new Exception("Product Not Found");
		}
		Product product=ifProductExist.get();
		product.setCategory(null);
		productRepo.deleteById(productId);
	}
}
