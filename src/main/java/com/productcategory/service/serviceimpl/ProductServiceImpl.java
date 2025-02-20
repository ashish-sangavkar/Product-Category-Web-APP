package com.productcategory.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.productcategory.model.Product;
import com.productcategory.repository.ProductRepository;
import com.productcategory.service.ProductService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }


	@Override
	public int deleteByProductId(int productId) {
		return this.productRepository.deleteByProductId(productId);
	}

	@Override
	public Product findByProductId(int productId) {
		return this.productRepository.findByProductId(productId);
	}

	@Override
	public Product save(Product product) {
		return this.productRepository.save(product);
	}

}
