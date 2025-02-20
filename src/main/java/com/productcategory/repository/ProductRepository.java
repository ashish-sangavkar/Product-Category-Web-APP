package com.productcategory.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.productcategory.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	public Product save(Product product);
	
	public Page<Product> findAll(Pageable pageable);
	
	public int deleteByProductId(int productId);
	
	public Product findByProductId(int productId);
}
