package com.productcategory.service;

import java.util.List;

import com.productcategory.model.Category;

public interface CategoryService {

	public Category save(Category category);
	
	public List<Category> findAll();
	
	public int deleteByCategoryId(int categoryId);
	
	public Category findByCategoryId(long categoryId);
}
