package com.productcategory.service.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productcategory.model.Category;
import com.productcategory.repository.CategoryRepository;
import com.productcategory.service.CategoryService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Category save(Category category) {
		return this.categoryRepository.save(category);
	}

	@Override
	public List<Category> findAll() {
		return this.categoryRepository.findAll();
	}

	@Override
	public int deleteByCategoryId(int categoryId) {
		return this.categoryRepository.deleteByCategoryId(categoryId);
	}

	@Override
	public Category findByCategoryId(long categoryId) {
		return this.categoryRepository.findByCategoryId(categoryId);
	}

}
