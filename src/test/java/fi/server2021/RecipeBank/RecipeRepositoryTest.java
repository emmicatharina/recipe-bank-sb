package fi.server2021.RecipeBank;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fi.server2021.RecipeBank.domain.CategoryRepository;
import fi.server2021.RecipeBank.domain.Recipe;
import fi.server2021.RecipeBank.domain.RecipeRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RecipeRepositoryTest {
	
	@Autowired
	private RecipeRepository repository;
	
	@Autowired
	private CategoryRepository crepository;
	
	@Test
	public void findByTitleShouldReturnSource() {
		List<Recipe> recipes = repository.findByTitle("Vaivaamaton pizza");
		assertThat(recipes.get(0).getSource()).isEqualTo("Pastanjauhantaa-blogi");
	}
	
	@Test
	public void insertNewRecipe() {
		Recipe recipe = new Recipe("Flammkuchen", 30, "https://www.hannasumari.fi/2019/flammkuchen-nopea-herkku-vahan-kuin-pizza/", "Hanna Sumari", crepository.findByName("friday").get(0));
		repository.save(recipe);
		List<Recipe> recipes = repository.findByTitle("Flammkuchen");
		assertThat(recipes.get(0).getTitle()).isEqualTo("Flammkuchen");
		//assertThat(recipe.getId()).isNotNull();
	}
	
	@Test
	public void deleteRecipe() {
		List<Recipe> recipes = repository.findByTitle("Tiramisu");
		repository.deleteById(recipes.get(0).getId());
		recipes = repository.findByTitle("Tiramisu");
		assertThat(recipes).hasSize(0);
	}

}
