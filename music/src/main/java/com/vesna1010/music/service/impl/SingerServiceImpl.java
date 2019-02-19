package com.vesna1010.music.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.vesna1010.music.exception.ResourceNotFoundException;
import com.vesna1010.music.model.Singer;
import com.vesna1010.music.repository.SingerRepository;
import com.vesna1010.music.service.SingerService;

@Service
@Transactional
public class SingerServiceImpl implements SingerService {

	@Autowired
	private SingerRepository repository;

	@Override
	public List<Singer> findAll(Sort sort) {
		return repository.findAll(sort);
	}

	@Override
	public Page<Singer> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public Singer findById(Long id) {
		Optional<Singer> optional = repository.findById(id);

		return optional.orElseThrow(() -> new ResourceNotFoundException("No singer found with id " + id));
	}

	@Override
	public Singer save(Singer singer) {
		return repository.save(singer);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

}
