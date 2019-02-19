package com.vesna1010.music.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.data.web.SortDefault.SortDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.vesna1010.music.model.Album;
import com.vesna1010.music.model.Song;
import com.vesna1010.music.service.AlbumService;
import com.vesna1010.music.service.SongService;
import com.vesna1010.music.validator.SongValidator;

@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
@Controller
public class SongsController {

	@Autowired
	private SongService songService;
	@Autowired
	private AlbumService albumService;
	@Autowired
	private SongValidator validator;

	@GetMapping("/songs")
	public ModelAndView renderPageWithSongs(@PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable) {
		return new ModelAndView("songs", "page", songService.findAll(pageable));
	}

	@GetMapping("/add_song")
	public ModelAndView renderSongForm() {
		return new ModelAndView("song_form", "song", new Song());
	}

	@PostMapping("/save_song")
	public ModelAndView saveSongAndRenderForm(@RequestParam MultipartFile mp3File, Song song, BindingResult result) {

		if (!mp3File.isEmpty()) {
			try {
				song.setFile(mp3File.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		validator.validate(song, result);

		if (result.hasErrors()) {
			return new ModelAndView("song_form");
		}

		songService.save(song);

		return new ModelAndView("redirect:/add_song");
	}

	@GetMapping("/edit_song")
	public ModelAndView renderFormWithSong(@RequestParam Long id) {
		return new ModelAndView("song_form", "song", songService.findById(id));
	}

	@GetMapping("/delete_song")
	public ModelAndView deleteSong(@RequestParam Long id) {
		songService.deleteById(id);

		return new ModelAndView("redirect:/songs");
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping(value = "/download_song", produces = "audio/mp3")
	public @ResponseBody byte[] downloadSong(@RequestParam Long id) {
		Song song = songService.findById(id);

		song.setCounter(song.getCounter() + 1);
		songService.save(song);

		return song.getFile();
	}

	@PreAuthorize("permitAll")
	@GetMapping(value = "/song", produces = "audio/mp3")
	public @ResponseBody byte[] getSongFile(@RequestParam Long id) {
		Song song = songService.findById(id);

		return song.getFile();
	}

	@PreAuthorize("permitAll")
	@ModelAttribute("albums")
	public List<Album> albums(@SortDefaults({ @SortDefault(sort = "title", direction = Direction.ASC),
			@SortDefault(sort = "id", direction = Direction.ASC) }) Sort sort) {
		return albumService.findAll(sort);
	}

}
