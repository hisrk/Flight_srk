package com.psa.flightapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
							  Model model,
							  HttpSession httpSession) {

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
				model.addAttribute("msg","invalid Username Or password");
				return "login/login";
			}
		} else {
			model.addAttribute("msg","invalid Username Or password");
			return "login/login";
		}
	}


	@PostMapping("/logout")
	public String logoutControl(HttpSession session){
		session.invalidate();
  return "login/login";
	}
}



