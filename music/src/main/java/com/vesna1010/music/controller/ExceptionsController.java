package com.vesna1010.music.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import com.vesna1010.music.exception.ResourceNotFoundException;

@ControllerAdvice
public class ExceptionsController {

	@ExceptionHandler
	public ModelAndView handleResourceNotFoundException(ResourceNotFoundException e) {
		return new ModelAndView("error", "message", e.getMessage());
	}

}
