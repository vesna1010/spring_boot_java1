package com.vesna1010.music.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.vesna1010.music.model.Album;
import com.vesna1010.music.model.Song;

@Component
public class SongValidator implements Validator {

	public static final String TITLE_REGEXP = "^[a-zA-Z0-9\\s]{3,}$";

	@Override
	public boolean supports(Class<?> cls) {
		return (cls == Song.class);
	}

	@Override
	public void validate(Object object, Errors errors) {
		Song song = (Song) object;

		if (isInvalidTitle(song.getTitle())) {
			errors.rejectValue("title", "song.title");
		}

		if (isInvalidAlbum(song.getAlbum())) {
			errors.rejectValue("album", "song.album");
		}

		if (isInvalidFile(song.getFile())) {
			errors.rejectValue("file", "song.file");
		}

	}

	private boolean isInvalidTitle(String title) {
		return (title == null || !title.matches(TITLE_REGEXP));
	}

	private boolean isInvalidAlbum(Album album) {
		return (album == null);
	}

	private boolean isInvalidFile(byte[] file) {
		return (file == null || file.length == 0);
	}

}
