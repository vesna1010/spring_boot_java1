package com.vesna1010.music.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.data.web.SortDefault.SortDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.vesna1010.music.service.AlbumService;

@Controller
public class HomeController {

	@Autowired
	private AlbumService service;

	@GetMapping(value = "/", params = "!value")
	public ModelAndView homePageWithAlbums(@PageableDefault(page = 0, size = 10) @SortDefaults({
			@SortDefault(sort = "releaseDate", direction = Direction.DESC),
			@SortDefault(sort = "id", direction = Direction.ASC) }) Pageable pageable) {
		return new ModelAndView("home", "page", service.findAll(pageable));
	}

	@GetMapping(value = "/", params = "value")
	public ModelAndView homePageWithAlbums(@RequestParam String value,
			@PageableDefault(page = 0, size = 10) @SortDefaults({
					@SortDefault(sort = "releaseDate", direction = Direction.DESC),
					@SortDefault(sort = "id", direction = Direction.ASC) }) Pageable pageable) {
		return new ModelAndView("home", "page", service.findAllByRequiredValue(value, pageable));
	}

}
