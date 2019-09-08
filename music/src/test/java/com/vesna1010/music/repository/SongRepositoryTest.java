package com.vesna1010.music.repository;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertNotNull;
import java.time.LocalDate;
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
public class SongRepositoryTest extends BaseRepositoryTest {

	@Autowired
	private SongRepository repository;

	@Test
	public void findAllByPageTest() {
		Page<Song> page2 = repository.findAll(PAGEABLE);
		List<Song> songs = page2.getContent();

		assertThat(page2.getTotalPages(), is(3));
		assertThat(songs, hasSize(2));
		assertThat(songs.get(0).getTitle(), is("Title SEARch C"));
		assertThat(songs.get(1).getTitle(), is("Title D"));
	}

	@Test
	public void findByIdTest() {
		Optional<Song> optional = repository.findById(1L);
		Song song = optional.get();

		assertThat(song.getTitle(), is("Title SEARch A"));
	}

	@Test
	public void findByIdNotFoundTest() {
		Optional<Song> optional = repository.findById(7L);

		assertFalse(optional.isPresent());
	}

	@Test
	public void saveTest() {
		Singer singer = new Singer(1L, "Singer search A", LocalDate.of(1987, 12, 1), getImage());
		Album album = new Album(1L, "Title search A", LocalDate.of(2019, 1, 2), singer, getSong());
		Song song = new Song("Title", album, getSong());

		song = repository.save(song);

		assertNotNull(song.getId());
		assertThat(repository.count(), is(7L));
	}

	@Test
	public void updateSongTest() {
		Optional<Song> optional = repository.findById(1L);
		Song song = optional.get();

		song.setTitle("New Title");

		song = repository.save(song);

		assertThat(song.getTitle(), is("New Title"));
		assertThat(repository.count(), is(6L));
	}

	@Test
	public void deleteByIdTest() {
		repository.deleteById(1L);

		assertFalse(repository.existsById(1L));
	}

}
