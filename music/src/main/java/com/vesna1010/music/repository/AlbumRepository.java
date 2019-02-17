package com.vesna1010.music.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.vesna1010.music.model.Album;

public interface AlbumRepository extends JpaRepository<Album, Long> {

	@Query("select distinct a from Album a join a.songs s where a.title like %:value%"
		   + " or a.singer.name like %:value% or s.title like %:value%")
	Page<Album> findAllByRequiredValue(@Param("value") String value, Pageable pageable);

}
