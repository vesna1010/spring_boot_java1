package com.vesna1010.music.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.vesna1010.music.model.User;
import com.vesna1010.music.service.UserService;
import com.vesna1010.music.validator.UserValidator;

@PreAuthorize("hasRole('ADMIN')")
@Controller
public class UsersController {

	@Autowired
	private UserService service;
	@Autowired
	private UserValidator validator;

	@GetMapping("/users")
	public ModelAndView renderPageWithUsers(@PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable) {
		return new ModelAndView("users", "page", service.findAll(pageable));
	}

	@PreAuthorize("isAnonymous() or hasRole('ADMIN')")
	@GetMapping("/add_user")
	public ModelAndView signUp() {
		return new ModelAndView("user_form", "user", new User());
	}

	@PreAuthorize("isAnonymous() or hasRole('ADMIN')")
	@PostMapping("/save_user")
	public ModelAndView saveUserAndRenderForm(User user, BindingResult result) {
		validator.validate(user, result);

		if (result.hasErrors()) {
			return new ModelAndView("user_form");
		}

		service.save(user);

		return new ModelAndView("redirect:/add_user");
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/edit_user")
	public ModelAndView renderFormWithUser(Principal principal) {
		return new ModelAndView("change_password", "user", service.findByEmail(principal.getName()));
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/save_password")
	public ModelAndView saveNewPassword(User user, BindingResult result) {
		validator.validate(user, result);

		if (result.hasFieldErrors("password") || result.hasFieldErrors("confirmPassword")) {
			return new ModelAndView("change_password");
		}

		service.save(user);

		return new ModelAndView("change_password", "message", "The password is successfully saved!");
	}

	@GetMapping("/delete_user")
	public ModelAndView deleteUser(@RequestParam Long id) {
		service.deleteById(id);

		return new ModelAndView("redirect:/users");
	}

	@GetMapping("/disable_user")
	public ModelAndView disableUser(@RequestParam Long id) {
		service.disableById(id);

		return new ModelAndView("redirect:/users");
	}

}
