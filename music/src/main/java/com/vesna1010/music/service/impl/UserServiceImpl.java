package com.vesna1010.music.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.vesna1010.music.model.User;
import com.vesna1010.music.enums.Role;
import com.vesna1010.music.exception.ResourceNotFoundException;
import com.vesna1010.music.repository.UserRepository;
import com.vesna1010.music.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Page<User> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Override
	public User findByEmail(String email) {
		Optional<User> optional = userRepository.findByEmail(email);

		return optional.orElseThrow(() -> new ResourceNotFoundException("No user found with email " + email));
	}

	@Override
	public User save(User user) {
		String password = user.getPassword();

		password = passwordEncoder.encode(password);
		user.setPassword(password);

		return userRepository.save(user);
	}

	@Override
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public void disableById(Long id) {
		userRepository.disableById(id);
	}

	@Override
	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> optional = userRepository.findByEmail(email);
		User user = optional.orElseThrow(() -> new UsernameNotFoundException("No user found with email " + email));
		Role role = user.getRole();
		String password = user.getPassword();
		List<GrantedAuthority> authorities = new ArrayList<>();

		authorities.add(new SimpleGrantedAuthority("ROLE_" + role.toString()));

		return new org.springframework.security.core.userdetails.User(email, password, user.isEnabled(), true, true, true, authorities);
	}

}
