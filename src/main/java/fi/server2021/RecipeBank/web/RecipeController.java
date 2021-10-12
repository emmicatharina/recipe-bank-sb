package fi.server2021.RecipeBank.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fi.server2021.RecipeBank.domain.CategoryRepository;
import fi.server2021.RecipeBank.domain.Recipe;
import fi.server2021.RecipeBank.domain.RecipeRepository;

@Controller
public class RecipeController {
	@Autowired
	private RecipeRepository repository;
	@Autowired
	private CategoryRepository crepository;

	// List all recipes
	@GetMapping("/recipelist")
	public String recipeList(Model model) {
		model.addAttribute("recipes", repository.findAll());
		return "recipelist";
	}
	
	// Add new book
	@GetMapping("/add")
	public String addRecipe(Model model) {
		model.addAttribute("recipe", new Recipe());
		model.addAttribute("categories", crepository.findAll());
		return "addrecipe";
	}
}
