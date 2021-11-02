package fi.server2021.RecipeBank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.server2021.RecipeBank.domain.Category;
import fi.server2021.RecipeBank.domain.CategoryRepository;
import fi.server2021.RecipeBank.domain.Recipe;
import fi.server2021.RecipeBank.domain.RecipeRepository;
import fi.server2021.RecipeBank.domain.User;
import fi.server2021.RecipeBank.domain.UserRepository;

@SpringBootApplication
public class RecipeBankApplication {
	private static final Logger log = LoggerFactory.getLogger(RecipeBankApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RecipeBankApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner recipeDemo(RecipeRepository rrepository, CategoryRepository crepository, UserRepository urepository) {
		return(args) -> {
			log.info("save a couple of categories");
			crepository.save(new Category("dessert"));
			crepository.save(new Category("friday"));
			
			log.info("save a couple of recipes");
			rrepository.save(new Recipe("Vaivaamaton pizza", 20, "http://pastanjauhantaa.blogspot.com/2012/03/vaivaamaton-pizza.html", "Pastanjauhantaa-blogi", crepository.findByName("friday").get(0)));
			rrepository.save(new Recipe("Tiramisu", 180, "https://www.meillakotona.fi/reseptit/italialainen-tiramisu", "Meillä kotona", crepository.findByName("dessert").get(0)));
			
			log.info("create users");
			urepository.save(new User("user", "$2a$10$3DepSPeCaXxFzXQbbvci4.A2x2ZvPBqwqMTdAfZUbJ4CoKRyMaE0.", "USER"));
			urepository.save(new User("admin", "$2a$10$g9lsZG/4BWjWbHHhM7SLUufUby0N07ACgiLNpJnAUfLor4Dqs.dhe", "ADMIN"));
			
			log.info("fetch all recipes");
			for (Recipe recipe : rrepository.findAll()) {
				log.info(recipe.toString());
			}
		};
	}

}
