package com.productcategory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.productcategory.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

	public Category save(Category category);
	
	public List<Category> findAll();
	
	public Category findByCategoryId(long categoryId);
	
	public int deleteByCategoryId(int categoryId);
	
}
