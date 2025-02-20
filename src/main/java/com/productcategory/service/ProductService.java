package com.productcategory.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.productcategory.model.Product;

public interface ProductService {

	public Product save(Product product);
	
	public Page<Product> findAll(Pageable pageable);
	
	public int deleteByProductId(int productId);
	
	public Product findByProductId(int productId);
}
