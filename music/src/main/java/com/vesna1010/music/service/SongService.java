package com.vesna1010.music.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.vesna1010.music.model.Song;

public interface SongService {

	Page<Song> findAll(Pageable pageable);
		
	Song findById(Long id);

	Song save(Song song);

	void deleteById(Long id);

}
