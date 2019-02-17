package com.vesna1010.music.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.vesna1010.music.model.Singer;

public interface SingerService {

	List<Singer> findAll();

	Page<Singer> findAll(Pageable pageable);

	Singer findById(Long id);

	Singer save(Singer singer);

	void deleteById(Long id);

}
