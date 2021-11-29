package fi.server2021.RecipeBank.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.server2021.RecipeBank.domain.Category;
import fi.server2021.RecipeBank.domain.CategoryRepository;
import fi.server2021.RecipeBank.domain.Recipe;
import fi.server2021.RecipeBank.domain.RecipeRepository;

@Controller
public class RecipeController {
	@Autowired
	private RecipeRepository repository;
	@Autowired
	private CategoryRepository crepository;
	
	// Main page
	@GetMapping("/index")
	public String index() {
		return "index";
	}

	// List all recipes
	@GetMapping("/recipelist")
	public String recipeList(Model model) {
		model.addAttribute("recipes", repository.findAll());
		return "recipelist";
	}
	
	// RESTful service to get all recipes
	@GetMapping("/recipes")
	public @ResponseBody List<Recipe> recipeListRest() {
		return (List<Recipe>) repository.findAll();
	}
	
	// RESTful service to get recipe by id
	@GetMapping("/recipe/{id}")
	public @ResponseBody Optional<Recipe> findRecipeRest(@PathVariable("id") Long recipeId) {
		return repository.findById(recipeId);
	}
	
	// RESTful service to get all categories
	@GetMapping("/categories")
	public @ResponseBody List<Category> categoryListRest() {
		return (List<Category>) crepository.findAll();
	}
		
	// RESTful service to get category by id
	@GetMapping("/category/{id}")
	public @ResponseBody Optional<Category> findCategoryRest(@PathVariable("id") Long catId) {
		return crepository.findById(catId);
	}
	
	// Add new recipe
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/add")
	public String addRecipe(Model model) {
		model.addAttribute("recipe", new Recipe());
		model.addAttribute("categories", crepository.findAll());
		return "addrecipe";
	}
	
	// Save new recipe
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/save")
	public String saveRecipe(Recipe recipe) {
		repository.save(recipe);
		return "redirect:recipelist";
	}
	
	// Delete recipe
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/delete/{id}")
	public String deleteRecipe(@PathVariable("id") Long recipeId, Model model) {
		repository.deleteById(recipeId);
		return "redirect:../recipelist";
	}
	
	// Edit recipe
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("edit/{id}")
	public String editRecipe(@PathVariable("id") Long recipeId, Model model) {
		model.addAttribute("recipe", repository.findById(recipeId));
		model.addAttribute("categories", crepository.findAll());
		return "editrecipe";
	}
	
	// Add new category
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/addcategory")
	public String addCategory(Model model) {
		model.addAttribute("category", new Category());
		return "addcategory";
	}
	
	// Save category
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/savecategory")
	public String saveCategory(@Valid Category category, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "addcategory";
		} 
		crepository.save(category);
		return "redirect:recipelist";
	}
	
}
