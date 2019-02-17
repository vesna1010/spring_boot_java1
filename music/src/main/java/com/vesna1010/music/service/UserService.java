package com.vesna1010.music.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.vesna1010.music.model.User;

public interface UserService extends UserDetailsService {

	Page<User> findAll(Pageable pageable);

	User findByEmail(String email);

	User save(User user);

	void deleteById(Long id);

	void disableById(Long id);
	
	boolean existsByEmail(String email);

}
