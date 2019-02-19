package com.vesna1010.music.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import com.vesna1010.music.model.Album;

public interface AlbumService {

	List<Album> findAll(Sort sort);

	Page<Album> findAll(Pageable pageable);
		
	Page<Album> findAllByRequiredValue(String value, Pageable pageable);

	Album findById(Long id);

	Album save(Album album);

	void deleteById(Long id);
	
}
