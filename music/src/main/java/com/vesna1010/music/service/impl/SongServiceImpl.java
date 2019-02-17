package com.vesna1010.music.service.impl;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.vesna1010.music.exception.ResourceNotFoundException;
import com.vesna1010.music.model.Song;
import com.vesna1010.music.repository.SongRepository;
import com.vesna1010.music.service.SongService;

@Service
@Transactional
public class SongServiceImpl implements SongService {

	@Autowired
	private SongRepository repository;

	@Override
	public Page<Song> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public Song findById(Long id) {
		Optional<Song> optional = repository.findById(id);

		return optional.orElseThrow(() -> new ResourceNotFoundException("No song found with id " + id));
	}

	@Override
	public Song save(Song song) {
		return repository.save(song);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

}
