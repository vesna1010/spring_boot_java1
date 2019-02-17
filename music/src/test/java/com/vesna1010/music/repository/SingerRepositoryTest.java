package com.vesna1010.music.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.jdbc.Sql;
import com.vesna1010.music.model.Singer;

@Sql(scripts = "classpath:sql/music.sql")
public class SingerRepositoryTest extends BaseRepositoryTest {

	@Autowired
	private SingerRepository repository;

	@Test
	public void findAllTest() {
		List<Singer> singers = repository.findAll();

		assertThat(singers, hasSize(4));
		assertThat(singers.get(0).getName(), is("Singer search A"));
		assertThat(singers.get(1).getName(), is("Singer B"));
		assertThat(singers.get(2).getName(), is("Singer C"));
		assertThat(singers.get(3).getName(), is("Singer D"));
	}

	@Test
	public void findAllByPageTest() {
		Pageable pageable = PageRequest.of(1, 2, new Sort(Direction.ASC, "id"));
		Page<Singer> page2 = repository.findAll(pageable);
		List<Singer> singers = page2.getContent();

		assertThat(page2.getTotalPages(), is(2));
		assertThat(singers, hasSize(2));
		assertThat(singers.get(0).getName(), is("Singer C"));
		assertThat(singers.get(1).getName(), is("Singer D"));
	}

	@Test
	public void findByIdTest() {
		Optional<Singer> optional = repository.findById(1L);
		Singer singer = optional.get();

		assertThat(singer.getName(), is("Singer search A"));
		assertThat(singer.getBirthDate(), is(LocalDate.of(1987, Month.DECEMBER, 1)));
		assertThat(singer.getAlbums(), hasSize(2));
	}

	@Test
	public void findByIdNotFoundTest() {
		Optional<Singer> optional = repository.findById(5L);

		assertFalse(optional.isPresent());
	}

	@Test
	public void saveTest() {
		Singer singer = new Singer("Singer E", LocalDate.of(1977, 11, 23), getImage());

		singer = repository.save(singer);

		assertNotNull(singer.getId());
		assertThat(repository.count(), is(5L));
	}

	@Test
	public void updateTest() {
		Singer singer = new Singer(1L, "Singer", LocalDate.of(1987, Month.DECEMBER, 1), getImage());

		singer = repository.save(singer);
		
		Optional<Singer> optional = repository.findById(1L);
		singer = optional.get();

		assertThat(singer.getName(), is("Singer"));
		assertThat(singer.getBirthDate(), is(LocalDate.of(1987, Month.DECEMBER, 1)));
		assertThat(singer.getAlbums(), hasSize(2));
		assertThat(repository.count(), is(4L));
	}

	@Test
	public void deleteTest() {
		repository.deleteById(1L);

		assertFalse(repository.existsById(1L));
	}

}
