package com.vesna1010.music.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.vesna1010.music.model.Singer;
import com.vesna1010.music.service.SingerService;
import com.vesna1010.music.validator.SingerValidator;

@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
@Controller
public class SingersController {

	@Autowired
	private SingerService service;
	@Autowired
	private SingerValidator validator;

	@GetMapping("/singers")
	public ModelAndView renderPageWithSingers(@PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable) {
		return new ModelAndView("singers", "page", service.findAll(pageable));
	}

	@GetMapping("/add_singer")
	public ModelAndView renderSingerForm() {
		return new ModelAndView("singer_form", "singer", new Singer());
	}

	@PostMapping("/save_singer")
	public ModelAndView saveSingerAndRenderForm(@RequestParam MultipartFile file, Singer singer, BindingResult result) {

		if (!file.isEmpty()) {
			try {
				singer.setPhoto(file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		validator.validate(singer, result);

		if (result.hasErrors()) {
			return new ModelAndView("singer_form");
		}

		service.save(singer);

		return new ModelAndView("redirect:/add_singer");
	}

	@GetMapping("/edit_singer")
	public ModelAndView renderFormWithSinger(@RequestParam Long id) {
		return new ModelAndView("singer_form", "singer", service.findById(id));
	}

	@PreAuthorize("permitAll")
	@GetMapping("/singer_details")
	public ModelAndView renderSingerDetails(@RequestParam Long id) {
		return new ModelAndView("singer_details", "singer", service.findById(id));
	}

	@GetMapping("/delete_singer")
	public ModelAndView deleteSinger(@RequestParam Long id) {
		service.deleteById(id);

		return new ModelAndView("redirect:/singers");
	}

}
