package com.vesna1010.music.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.jdbc.Sql;
import com.vesna1010.music.enums.Role;
import com.vesna1010.music.model.User;
import com.vesna1010.music.repository.UserRepository;

@Transactional
@Sql(scripts = "classpath:sql/users.sql")
public class UserRepositoryTest extends BaseRepositoryTest {

	@Autowired
	private UserRepository repository;
	@Autowired
	private PasswordEncoder encoder;

	@Test
	public void findAllTest() {
		Page<User> page2 = repository.findAll(PAGEABLE);
		List<User> users = page2.getContent();

		assertThat(page2.getTotalPages(), is(2));
		assertThat(users, hasSize(2));
		assertThat(users.get(0).getEmail(), is("emailC@gmail.com"));
		assertThat(users.get(1).getEmail(), is("emailD@gmail.com"));
	}

	@Test
	public void findByEmailTest() {
		Optional<User> optional = repository.findByEmail("emailA@gmail.com");
		User user = optional.get();

		assertThat(user.getName(), is("Name A"));
		assertThat(user.getRole(), is(Role.ADMIN));
		assertTrue(user.isEnabled());
	}

	@Test
	public void findByEmailNotFoundTest() {
		Optional<User> optional = repository.findByEmail("emailE@gmail.com");

		assertFalse(optional.isPresent());
	}

	@Test
	public void saveTest() {
		User user = new User("Name E", "emailE@gmail.com", encoder.encode("PasswordE"), true, Role.USER);

		user = repository.save(user);

		assertNotNull(user.getId());
		assertThat(repository.count(), is(5L));
	}

	@Test
	public void updateTest() {
		Optional<User> optional = repository.findByEmail("emailA@gmail.com");
		User user = optional.get();

		user.setEnabled(false);

		user = repository.save(user);

		assertFalse(user.isEnabled());
		assertThat(repository.count(), is(4L));
	}

	@Test
	public void deleteByIdTest() {
		repository.deleteById(1L);

		assertFalse(repository.existsById(1L));
	}

	@Test
	public void disableByIdTest() {
		repository.disableById(1L);

		Optional<User> optional = repository.findById(1L);
		User user = optional.get();

		assertFalse(user.isEnabled());
	}

	@Test
	public void existsByEmailTest() {
		assertTrue(repository.existsByEmail("emailA@gmail.com"));
	}

	@Test
	public void notExistsByEmailTest() {
		assertFalse(repository.existsByEmail("emailE@gmail.com"));
	}

}
