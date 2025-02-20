package com.productcategory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.productcategory.model.Category;
import com.productcategory.model.Product;
import com.productcategory.service.CategoryService;

@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/addNewCategoryPage")
	public String getAddNewCategoryPage(Model model) {
		Category category = new Category();
		model.addAttribute("category", category);
		return "addNewCategoryPage";
	}
	@PostMapping(value="/createNewCategory")
	public String createNewCategory(@ModelAttribute("category") Category Category) {
		this.categoryService.save(Category);
		return "redirect:/findAllCategories";
	}
	
	@GetMapping("/findAllCategories")
	public String findAllCategories(Model model){
		model.addAttribute("categories", this.categoryService.findAll());
		return "categories";
	}
	
	
	@GetMapping("/updateCategory/{categoryId}")
	public String getUpdateCategoryPage(@PathVariable("categoryId") long categoryId, Model model) {
		model.addAttribute("category", categoryService.findByCategoryId(categoryId));
		return "updateCategory";
	}
	
	@PostMapping("/updateOldCategory/{categoryId}")
	public String updateCategory(@PathVariable("categoryId") long categoryId, @ModelAttribute("category") Category category, Model model) {
		Category category1 = categoryService.findByCategoryId(categoryId);
		category1.setCategoryName(category.getCategoryName());
		this.categoryService.save(category1);
		return "redirect:/findAllCategories";
	}
	
	@GetMapping(value="/deleteCategoryById/{categoryId}")
	public String deleteCategoryById(@PathVariable int categoryId){
		this.categoryService.deleteByCategoryId(categoryId);
		return "redirect:/findAllCategories";
	}
	
	@GetMapping(value="/findCategoryByCategoryId/{categoryId}")
	public Category findCategoryByCategoryId(@PathVariable int categoryId) {
		return this.categoryService.findByCategoryId(categoryId);
	}
} 
