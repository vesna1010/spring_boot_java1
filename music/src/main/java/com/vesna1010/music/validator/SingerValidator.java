package com.vesna1010.music.validator;

import java.time.LocalDate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.vesna1010.music.model.Singer;

@Component
public class SingerValidator implements Validator {

	public static final String NAME_REGEXP = "^[a-zA-Z\\s]{5,}$";

	@Override
	public boolean supports(Class<?> cls) {
		return (cls == Singer.class);
	}

	@Override
	public void validate(Object object, Errors errors) {
		Singer singer = (Singer) object;

		if (isInvalidName(singer.getName())) {
			errors.rejectValue("name", "singer.name");
		}

		if (isInvalidPhoto(singer.getPhoto())) {
			errors.rejectValue("photo", "singer.photo");
		}

		if (isInvalidBirthDate(singer.getBirthDate())) {
			errors.rejectValue("birthDate", "singer.birthDate");
		}

	}

	private boolean isInvalidName(String name) {
		return (name == null || !name.matches(NAME_REGEXP));
	}

	private boolean isInvalidPhoto(byte[] photo) {
		return (photo == null || photo.length == 0);
	}

	private boolean isInvalidBirthDate(LocalDate birthDate) {
		return (birthDate == null);
	}

}
