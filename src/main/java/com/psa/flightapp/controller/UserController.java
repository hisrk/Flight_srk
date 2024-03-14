package com.psa.flightapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.psa.flightapp.entities.User;
import com.psa.flightapp.repositories.UserRepository;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	UserRepository userRepo;

	@GetMapping("/showReg")
	public String showRegistrationPage() {
		return "login/newRegistration";
	}

	@RequestMapping(path = "/showLogin")
	public String showLoginPage() {
		return "login/login";
	}

	@RequestMapping(path = "/saveReg")
	public String saveNewRegistration(@ModelAttribute("user") User user) {
		userRepo.save(user);
		return "login/login";
	}

	@PostMapping("/verifyLogin")
	public String verifyLogin(@RequestParam("email") String email,
							  @RequestParam("password") String password,
							  RedirectAttributes redirectAttributes,
							  HttpSession httpSession) {
		// Your user authentication logic goes here
		// Assuming you have a User entity with a field called "role"
		User user = userRepo.findByEmail(email);
		if (user != null && user.getPassword().equals(password)) {
			String role = user.getRole();
			if ("admin".equals(role)) {

				httpSession.setAttribute("loggedInUser", user.getEmail());

				return "login/adminHomePage";
			} else if ("user".equals(role)) {
				httpSession.setAttribute("loggedInUser", user.getEmail());

				return "searchFlights";
			} else {
				redirectAttributes.addFlashAttribute("error", "Invalid role");
				return "login/login";
			}
		} else {
			redirectAttributes.addFlashAttribute("error", "Invalid username/password");
			return "redirect:/login";
		}
	}


	@PostMapping("/logout")
	public String logoutControl(HttpSession session){
		session.invalidate();
  return "login/login";
	}
}



