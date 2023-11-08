package com.kaminari.WebThing;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Optional;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
	@Autowired UserRepository userRepository;
	@Autowired ItemService itemService;

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/logout")
	public String logout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(request, response, authentication);
		return "redirect:/login";
	}

	@GetMapping("/help")
	public String help() {
		return "help";
	}

	@GetMapping("/mypage")
	public String mypage(Principal principal, Model model, HttpServletRequest request) {
		Optional<User> userFromRepository = userRepository.findById(principal.getName());
		if (userFromRepository.isPresent()) {
			// Set user info
			User user = userFromRepository.get();
			model.addAttribute("username", user.getUsername());
			model.addAttribute("name", user.getName());
			model.addAttribute("lastSignIn", new SimpleDateFormat("dd-MM-yyyy").format(user.getLastSignIn()));
			// Set items
			model.addAttribute("items", itemService.allItems());
			// Return mypage.html template
			return "mypage";
		} else {
			model.addAttribute("error", "ユーザーが見つけませんでした。");
			return "error";
		}
	}

	@GetMapping("/myitems")
	public String myitems(Model model) {
		model.addAttribute("items", itemService.allItems());
		return "myitems/all";
	}

	@GetMapping("/myitems/new")
	public String myitemsNew() {
		return "myitems/new";
	}

	@GetMapping("/mypage/show")
	public String show(@RequestParam(name = "message", required = false, defaultValue = "") String message,
						Model model) {
		model.addAttribute("message", message);
		return "show";
	}
}