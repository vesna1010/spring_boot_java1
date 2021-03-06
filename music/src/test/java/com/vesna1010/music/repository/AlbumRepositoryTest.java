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
import org.springframework.test.context.jdbc.Sql;
import com.vesna1010.music.model.Album;
import com.vesna1010.music.model.Singer;
import com.vesna1010.music.model.Song;

@Sql(scripts = "classpath:sql/music.sql")
public class AlbumRepositoryTest extends BaseRepositoryTest {

	@Autowired
	private AlbumRepository repository;

	@Test
	public void findAllTest() {
		List<Album> albums = repository.findAll();

		assertThat(albums, hasSize(5));
		assertThat(albums.get(0).getTitle(), is("Title search A"));
		assertThat(albums.get(1).getTitle(), is("Title B"));
		assertThat(albums.get(2).getTitle(), is("Title C"));
		assertThat(albums.get(3).getTitle(), is("Title D"));
		assertThat(albums.get(4).getTitle(), is("Title E"));
	}

	@Test
	public void findAllByPageTest() {
		Page<Album> page2 = repository.findAll(PAGEABLE);
		List<Album> albums = page2.getContent();

		assertThat(page2.getTotalPages(), is(3));
		assertThat(albums, hasSize(2));
		assertThat(albums.get(0).getTitle(), is("Title C"));
		assertThat(albums.get(1).getTitle(), is("Title D"));
	}

	@Test
	public void findAllByTitleContainsOrSingerNameContainsOrSongsTitleContainsByPageTest() {
		Page<Album> page2 = repository.findAllByRequiredValue("sEaR", PAGEABLE);
		List<Album> albums = page2.getContent();

		assertThat(page2.getTotalPages(), is(2));
		assertThat(albums, hasSize(1));
		assertThat(albums.get(0).getTitle(), is("Title D"));
	}

	@Test
	public void findByIdTest() {
		Optional<Album> optional = repository.findById(1L);
		Album album = optional.get();

		assertThat(album.getTitle(), is("Title search A"));
		assertThat(album.getReleaseDate(), is(LocalDate.of(2019, 1, 2)));
		assertThat(album.getSongs(), hasSize(2));
	}

	@Test
	public void findByIdNotFoundTest() {
		Optional<Album> optional = repository.findById(6L);

		assertFalse(optional.isPresent());
	}

	@Test
	public void saveTest() {
		Singer singer = new Singer(1L, "Singer search A", LocalDate.of(1987, 12, 1), getImage());
		Album album = new Album("Title F", LocalDate.of(2019, Month.JANUARY, 7), singer, getImage());
		album.addSong(new Song("Title", getSong()));

		album = repository.save(album);

		assertNotNull(album.getId());
		assertThat(repository.count(), is(6L));
	}

	@Test
	public void updateTest() {
		Optional<Album> optional = repository.findById(1L);
		Album album = optional.get();

		album.setTitle("New Title");

		album = repository.save(album);

		assertThat(album.getTitle(), is("New Title"));
		assertThat(album.getReleaseDate(), is(LocalDate.of(2019, 1, 2)));
		assertThat(album.getSongs(), hasSize(2));
	}

	@Test
	public void deleteTest() {
		repository.deleteById(1L);

		assertFalse(repository.existsById(1L));
	}

}
