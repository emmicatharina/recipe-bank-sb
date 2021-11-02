package fi.server2021.RecipeBank.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
	
	// Add new recipe
	@GetMapping("/add")
	public String addRecipe(Model model) {
		model.addAttribute("recipe", new Recipe());
		model.addAttribute("categories", crepository.findAll());
		return "addrecipe";
	}
	
	// Save new recipe
	@PostMapping("/save")
	public String saveRecipe(Recipe recipe) {
		repository.save(recipe);
		return "redirect:recipelist";
	}
	
	// Delete recipe
	@GetMapping("/delete/{id}")
	public String deleteRecipe(@PathVariable("id") Long recipeId, Model model) {
		repository.deleteById(recipeId);
		return "redirect:../recipelist";
	}
	
	// Edit recipe
	@GetMapping("edit/{id}")
	public String editRecipe(@PathVariable("id") Long recipeId, Model model) {
		model.addAttribute("recipe", repository.findById(recipeId));
		model.addAttribute("categories", crepository.findAll());
		return "editrecipe";
	}
	
}
