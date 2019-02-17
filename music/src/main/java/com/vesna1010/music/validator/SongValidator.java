package com.vesna1010.music.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.vesna1010.music.model.Album;
import com.vesna1010.music.model.Song;

@Component
public class SongValidator implements Validator {

	private String titleRegex = "^[a-zA-Z0-9\\s]{3,}$";

	@Override
	public boolean supports(Class<?> cls) {
		return (cls == Song.class);
	}

	@Override
	public void validate(Object object, Errors errors) {
		Song song = (Song) object;
		String title = song.getTitle();
		Album album = song.getAlbum();
		byte[] file = song.getFile();

		if (!title.matches(titleRegex)) {
			errors.rejectValue("title", "song.title");
		}

		if (album == null) {
			errors.rejectValue("album", "song.album");
		}

		if (file == null || file.length == 0) {
			errors.rejectValue("file", "song.file");
		}

	}

}
