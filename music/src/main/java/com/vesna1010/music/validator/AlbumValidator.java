package com.vesna1010.music.validator;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.vesna1010.music.model.Album;
import com.vesna1010.music.model.Singer;
import com.vesna1010.music.model.Song;

@Component
public class AlbumValidator implements Validator {

	private String titleRegex = "^[a-zA-Z0-9\\s]{3,}$";

	@Override
	public boolean supports(Class<?> cls) {
		return (cls == Album.class);
	}

	@Override
	public void validate(Object object, Errors errors) {
		Album album = (Album) object;
		String title = album.getTitle();
		LocalDate releaseDate = album.getReleaseDate();
		Singer singer = album.getSinger();
		byte[] logo = album.getLogo();
		List<Song> songs = album.getSongs();

		if (!title.matches(titleRegex)) {
			errors.rejectValue("title", "album.title");
		}

		if (releaseDate == null) {
			errors.rejectValue("releaseDate", "album.releaseDate");
		}

		if (singer == null) {
			errors.rejectValue("singer", "album.singer");
		}

		if (logo == null || logo.length == 0) {
			errors.rejectValue("logo", "album.logo");
		}

		if (songs.isEmpty()) {
			errors.rejectValue("songs", "album.songs");
		}

	}

}
