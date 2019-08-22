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

	public static final String TITLE_REGEXP = "^[a-zA-Z0-9\\s]{3,}$";

	@Override
	public boolean supports(Class<?> cls) {
		return (cls == Album.class);
	}

	@Override
	public void validate(Object object, Errors errors) {
		Album album = (Album) object;

		if (isInvalidTitle(album.getTitle())) {
			errors.rejectValue("title", "album.title");
		}

		if (isInvalidReleaseDate(album.getReleaseDate())) {
			errors.rejectValue("releaseDate", "album.releaseDate");
		}

		if (isInvalidSinger(album.getSinger())) {
			errors.rejectValue("singer", "album.singer");
		}

		if (isInvalidLogo(album.getLogo())) {
			errors.rejectValue("logo", "album.logo");
		}

		if (isInvalidSongs(album.getSongs())) {
			errors.rejectValue("songs", "album.songs");
		}

	}

	private boolean isInvalidTitle(String title) {
		return (title == null || !title.matches(TITLE_REGEXP));
	}

	private boolean isInvalidReleaseDate(LocalDate releaseDate) {
		return (releaseDate == null);
	}

	private boolean isInvalidSinger(Singer singer) {
		return (singer == null);
	}

	private boolean isInvalidLogo(byte[] logo) {
		return (logo == null || logo.length == 0);
	}

	private boolean isInvalidSongs(List<Song> songs) {
		return (songs == null || songs.isEmpty());
	}

}
