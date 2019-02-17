package com.vesna1010.music.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.vesna1010.music.model.Album;
import com.vesna1010.music.exception.ResourceNotFoundException;
import com.vesna1010.music.repository.AlbumRepository;
import com.vesna1010.music.service.AlbumService;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {

	@Autowired
	private AlbumRepository repository;

	@Override
	public List<Album> findAll() {
		return repository.findAll();
	}

	@Override
	public Page<Album> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public Page<Album> findAllByRequiredValue(String value, Pageable pageable) {
		return repository.findAllByRequiredValue(value, pageable);
	}

	@Override
	public Album findById(Long id) {
		Optional<Album> optional = repository.findById(id);

		return optional.orElseThrow(() -> new ResourceNotFoundException("No album found with id " + id));
	}

	@Override
	public Album save(Album album) {
		return repository.save(album);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

}
