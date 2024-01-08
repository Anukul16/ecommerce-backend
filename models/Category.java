package com.ecommerce.ecommerceapp.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.*;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long categoryId;
	
	@Column(name ="category_title",nullable = false)
	private String title;
	
	@Column(name = "category_description",nullable = false)
	private String description;
	
	@Column(name = "category_imgURL",nullable = false)
	private String imgURL;

	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Product>products=new ArrayList<Product>();
	
	public Long getId() {
		return categoryId;
	}

	public void setId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

	public Category(String title, String description, String imgURL) {
		super();
		this.title = title;
		this.description = description;
		this.imgURL = imgURL;
	}

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
