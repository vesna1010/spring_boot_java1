package com.vesna1010.music.validator;

import java.time.LocalDate;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.vesna1010.music.model.Singer;

@Component
public class SingerValidator implements Validator {

	private String nameRegex = "^[a-zA-Z\\s]{5,}$";

	@Override
	public boolean supports(Class<?> cls) {
		return (cls == Singer.class);
	}

	@Override
	public void validate(Object object, Errors errors) {
		Singer singer = (Singer) object;
		String name = singer.getName();
		LocalDate birthDate = singer.getBirthDate();
		byte[] photo = singer.getPhoto();

		if (!name.matches(nameRegex)) {
			errors.rejectValue("name", "singer.name");
		}

		if (birthDate == null) {
			errors.rejectValue("birthDate", "singer.birthDate");
		}

		if (photo == null || photo.length == 0) {
			errors.rejectValue("photo", "singer.photo");
		}

	}

}
