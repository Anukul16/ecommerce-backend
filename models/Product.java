package com.ecommerce.ecommerceapp.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;
	
	@Column(name = "product_name",nullable = false)
	private  String productName;
	
	@Column(name = "product_imgURL",nullable = false)
	private String productImgURL;
	
	@Column(name = "product_price",nullable = false)
	private String productPrice;
	
	@Column(name = "product_description",nullable = false)
	private String productDescription;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	@JoinColumn(name = "category_id",nullable = false)
	private Category category;
	 


	public Long getProductId() {
		return productId;
	}


	public void setProductId(Long productId) {
		this.productId = productId;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getProductImgURL() {
		return productImgURL;
	}


	public void setProductImgURL(String productImgURL) {
		this.productImgURL = productImgURL;
	}


	public String getProductPrice() {
		return productPrice;
	}


	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}


	public String getProductDescription() {
		return productDescription;
	}


	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public Product(Long productId, String productName, String productImgURL, String productPrice,
			String productDescription, Category category) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productImgURL = productImgURL;
		this.productPrice = productPrice;
		this.productDescription = productDescription;
		this.category = category;
	}


	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
