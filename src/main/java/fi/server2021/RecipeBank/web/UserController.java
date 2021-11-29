package fi.server2021.RecipeBank.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fi.server2021.RecipeBank.domain.RegistrationInformation;
import fi.server2021.RecipeBank.domain.User;
import fi.server2021.RecipeBank.domain.UserRepository;

@Controller
public class UserController {
	@Autowired
	private UserRepository repository;
	
	// Login form
	@GetMapping("/login")
	public String login() {
		return "login";
	}
		
	//Login form with error
	@GetMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login";
	}
	
	@GetMapping("register")
	public String addUser(Model model) {
		model.addAttribute("registrationInformation", new RegistrationInformation());
		return "registration";
	}
 
	@PostMapping("saveuser")
	public String save(@Valid @ModelAttribute("registrationInformation") RegistrationInformation registrationInformation,
			BindingResult bindingResult) {
		
		if (!bindingResult.hasErrors() ) {
			if (registrationInformation.getPassword().equals(registrationInformation.getPasswordCheck())) {
				String pwd = registrationInformation.getPassword();
				BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
				String hashPwd = bc.encode(pwd);
				
				User newUser = new User();
				newUser.setPasswordHash(hashPwd);
				newUser.setUsername(registrationInformation.getUsername());
				newUser.setRole("USER");
				if (repository.findByUsername(registrationInformation.getUsername()) == null) {
					repository.save(newUser);
				} else {
					bindingResult.rejectValue("username", "err.username", "Username exists already");
					return "registration";
				}
			} else {
				bindingResult.rejectValue("passwordCheck","err.passCheck", "Passwords don't match");
				return "registration";
			}
		} else {
			return "registration";
			
		}
		return "redirect:/login";
	}
}
