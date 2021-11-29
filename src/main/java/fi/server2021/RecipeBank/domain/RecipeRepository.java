package fi.server2021.RecipeBank.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long>{
	List<Recipe> findByTitle(String title);

}
