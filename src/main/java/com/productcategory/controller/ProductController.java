package com.productcategory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.productcategory.model.Category;
import com.productcategory.model.Product;
import com.productcategory.service.CategoryService;
import com.productcategory.service.ProductService;


@Controller
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/api/home")
	public String getHomePage() {
		return "index";
	}
	
	@GetMapping("/api/aboutus")
	public String getAboutusPage() {
		return "aboutus";
	}
	
	@GetMapping("/addNewProductPage")
	public String addNewProductPage(Model model) {
	    Product product = new Product();
	    model.addAttribute("product", product);
	    model.addAttribute("categories", this.categoryService.findAll()); // Ensure this is the correct attribute name
	    return "addNewProductPage";
	}
	
	@PostMapping("/createNewProduct")
	public String createNewProduct(@RequestParam int categoryId, @ModelAttribute Product product) {
	    Category category = this.categoryService.findByCategoryId(categoryId);
	    product.setCategory(category);
	    this.productService.save(product);
	    
	    // Redirect to the products page
	    return "redirect:/findAllProducts";
	}
	
	@GetMapping("/findAllProducts")
    public String findAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productService.findAll(pageable);
        
        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        
        return "products";
    }
	
	@GetMapping("/findProductByProductId/{productId}")
	public Product findProductByProductId(@PathVariable int productId) {
		return this.productService.findByProductId(productId);
	}
	
	@GetMapping("/updateProduct/{productId}")
	public String getUpdateProductPage(@PathVariable("productId") int productId, Model model) {
		model.addAttribute("product", this.productService.findByProductId(productId));
		model.addAttribute("categories", this.categoryService.findAll()); // Ensure this is the correct attribute name
		
		return "updateProduct";
	}
	
	@PostMapping("/updateOldProduct/{productId}")
	public String updateProduct(@RequestParam int categoryId,  @PathVariable("productId") int productId, @ModelAttribute("product") Product product, Model model) {
		Product product1 = productService.findByProductId(productId);
		product1.setProductName(product.getProductName());
		product1.setProductPrice(product.getProductPrice());
		product1.setProductDescription(product.getProductDescription()); 
		Category category = this.categoryService.findByCategoryId(categoryId);
		product1.setCategory(category);
		this.productService.save(product1);
		return "redirect:/findAllProducts";
	}
	
	@GetMapping("/deleteProductById/{productId}")
	public String deleteProductById(@PathVariable int productId) {
		this.productService.deleteByProductId(productId);
		return "redirect:/findAllProducts";
	}
}
