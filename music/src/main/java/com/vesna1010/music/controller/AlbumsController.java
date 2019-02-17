package com.vesna1010.music.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.vesna1010.music.model.Album;
import com.vesna1010.music.model.Singer;
import com.vesna1010.music.model.Song;
import com.vesna1010.music.service.AlbumService;
import com.vesna1010.music.service.SingerService;
import com.vesna1010.music.validator.AlbumValidator;

@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
@Controller
public class AlbumsController {

	@Autowired
	private AlbumService albumService;
	@Autowired
	private SingerService singerService;
	@Autowired
	private AlbumValidator validator;

	@GetMapping("/albums")
	public ModelAndView renderPageWithAlbums(@PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable) {
		return new ModelAndView("albums", "page", albumService.findAll(pageable));
	}

	@GetMapping("/add_album")
	public ModelAndView renderAlbumForm() {
		return new ModelAndView("album_form", "album", new Album());
	}

	@PostMapping("/save_album")
	public ModelAndView saveAlbumAndRenderForm(@RequestParam MultipartFile fileLogo,
			@RequestParam List<MultipartFile> fileSongs, Album album, BindingResult result) {

		if (!fileLogo.isEmpty()) {
			try {
				album.setLogo(fileLogo.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		for (MultipartFile file : fileSongs) {
			if (!file.isEmpty()) {
				try {
					album.addSong(new Song(file.getOriginalFilename(), file.getBytes()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		validator.validate(album, result);

		if (result.hasErrors()) {
			return new ModelAndView("album_form");
		}

		albumService.save(album);

		return new ModelAndView("redirect:/add_album");
	}

	@GetMapping("/edit_album")
	public ModelAndView renderAlbumFormWithAlbum(@RequestParam Long id) {
		return new ModelAndView("album_form", "album", albumService.findById(id));
	}

	@PreAuthorize("permitAll")
	@GetMapping("/album_details")
	public ModelAndView renderAlbumDetails(@RequestParam Long id) {
		return new ModelAndView("album_details", "album", albumService.findById(id));
	}

	@GetMapping("/delete_album")
	public ModelAndView deleteAlbum(@RequestParam Long id) {
		albumService.deleteById(id);

		return new ModelAndView("redirect:/albums");
	}

	@PreAuthorize("permitAll")
	@ModelAttribute(value = "singers")
	public List<Singer> singers() {
		List<Singer> singers = singerService.findAll();

		Collections.sort(singers, new SingerComparator());

		return singers;
	}

	private class SingerComparator implements Comparator<Singer> {

		@Override
		public int compare(Singer singer1, Singer singer2) {
			Comparator<Singer> comparator = Comparator.comparing(Singer::getName);

			comparator = comparator.thenComparing(Singer::getId);

			return comparator.compare(singer1, singer2);
		}

	}

}
