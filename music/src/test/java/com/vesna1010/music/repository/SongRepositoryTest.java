package com.vesna1010.music.repository;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertNotNull;
import java.util.List;
import java.util.Optional;
import javax.persistence.TypedQuery;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.jdbc.Sql;
import com.vesna1010.music.model.Album;
import com.vesna1010.music.model.Song;

@Sql(scripts = "classpath:sql/music.sql")
public class SongRepositoryTest extends BaseRepositoryTest {

	@Autowired
	private SongRepository repository;

	@Test
	public void findAllByPageTest() {
		Pageable pageable = PageRequest.of(1, 2, new Sort(Direction.ASC, "id"));
		Page<Song> page2 = repository.findAll(pageable);
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
		Album album = loadAlbumById(1L);
		Song song = new Song("Title", album, getSong());
		
		song = repository.save(song);

		assertNotNull(song.getId());
		assertThat(repository.count(), is(7L));
	}
	
	@Test
	public void updateSongTest() {
		Album album = loadAlbumById(1L);
		Song song = new Song(1L, "New Title", album, getSong());

		song = repository.save(song);
		
		Optional<Song> optional = repository.findById(1L);
		song = optional.get();

		assertThat(song.getTitle(), is("New Title"));
		assertThat(repository.count(), is(6L));
	}
	
	public Album loadAlbumById(Long id) {
		TypedQuery<Album> query = entityManager.createQuery("select a from Album a where a.id=:id", Album.class);

		query.setParameter("id", id);

		return query.getSingleResult();
	} 
	
	@Test
	public void deleteByIdTest() {
		repository.deleteById(1L);

		assertFalse(repository.existsById(1L));
	}

}
