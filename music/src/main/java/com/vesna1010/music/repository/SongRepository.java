package com.vesna1010.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vesna1010.music.model.Song;

public interface SongRepository extends JpaRepository<Song, Long> {

}
