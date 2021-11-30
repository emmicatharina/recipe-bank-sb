package fi.server2021.RecipeBank;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RunWith(SpringRunner.class)
@EnableWebMvc
@SpringBootTest
public class RecipeBankRestTest {
	
	@Autowired
	private WebApplicationContext webAppContext;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
	}
	
	@Test
	public void statusOk() throws Exception {
		mockMvc.perform(get("/recipes")).andExpect(status().isOk());
	}
	
	@Test
	public void responseTypeApplicationJson() throws Exception {
		mockMvc.perform(get("/recipes"))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
		
	}
	
	@Test
	public void apiStatusOk() throws Exception {
		mockMvc.perform(get("/api/recipes")).andExpect(status().isOk());
	}

}
