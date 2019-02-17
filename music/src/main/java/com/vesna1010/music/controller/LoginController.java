package com.vesna1010.music.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@GetMapping("/login")
	public ModelAndView loginForm() {
		return new ModelAndView("login_form");
	}

	@GetMapping("/denied")
	public ModelAndView deniedPage() {
		return new ModelAndView("denied");
	}

}
