package com.vesna1010.music.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.vesna1010.music.model.User;
import com.vesna1010.music.service.UserService;

@Component
public class UserValidator implements Validator {

	@Autowired
	private UserService service;
	private String nameRegex = "^[a-zA-Z\\s]{5,}$";
	private String emailPattern = "^[a-zA-Z0-9_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
	private String passwordPattern = "^\\S{8,15}$";

	@Override
	public boolean supports(Class<?> cls) {
		return (cls == User.class);
	}

	@Override
	public void validate(Object object, Errors errors) {
		User user = (User) object;
		String name = user.getName();
		String email = user.getEmail();
		String password = user.getPassword();
		String confirmPassword = user.getConfirmPassword();

		if (!name.matches(nameRegex)) {
			errors.rejectValue("name", "user.name");
		}

		if (!email.matches(emailPattern)) {
			errors.rejectValue("email", "user.email.pattern");
		}

		if (service.existsByEmail(email)) {
			errors.rejectValue("email", "user.email.exists");
		}

		if (!password.matches(passwordPattern)) {
			errors.rejectValue("password", "user.password");
		} else {
			if (!password.equals(confirmPassword)) {
				errors.rejectValue("confirmPassword", "user.passwordConfirmDifferent");
			}
		}

	}

}
