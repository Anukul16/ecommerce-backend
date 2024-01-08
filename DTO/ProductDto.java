package com.ecommerce.ecommerceapp.DTO;

import jakarta.persistence.Column;

public class ProductDto {

	private Long productId;
	
	private  String productName;
	
	
	private String productImgURL;
	
	
	private String productPrice;
	
	
	private String productDescription;
	
	@Column(nullable = false)
	private Long categoryId;

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

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public ProductDto(Long productId, String productName, String productImgURL, String productPrice,
			String productDescription, Long categoryId) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productImgURL = productImgURL;
		this.productPrice = productPrice;
		this.productDescription = productDescription;
		this.categoryId = categoryId;
	}

	public ProductDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
