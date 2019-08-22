package com.vesna1010.music.model;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "ALBUMS")
public class Album {

	private Long id;
	private String title;
	private LocalDate releaseDate;
	private Singer singer;
	private byte[] logo;
	private List<Song> songs = new ArrayList<>();

	public Album() {
	}

	public Album(String title, LocalDate releaseDate, Singer singer, byte[] logo) {
		this(null, title, releaseDate, singer, logo);
	}

	public Album(Long id, String title, LocalDate releaseDate, Singer singer, byte[] logo) {
		this.id = id;
		this.title = title;
		this.releaseDate = releaseDate;
		this.singer = singer;
		this.logo = logo;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "TITLE", nullable = false)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@DateTimeFormat(pattern = "dd.MM.yyyy")
	@Column(name = "RELEASE_DATE", nullable = false)
	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	@ManyToOne
	@JoinColumn(name = "SINGER_ID", nullable = false)
	public Singer getSinger() {
		return singer;
	}

	public void setSinger(Singer singer) {
		this.singer = singer;
	}

	@Lob
	@Column(name = "LOGO", nullable = false)
	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	@Transient
	public String getLogoStr() {
		String logoStr = null;

		try {
			logoStr = new String(Base64.getEncoder().encode(this.logo), "UTF-8");
		} catch (UnsupportedEncodingException | NullPointerException e) {
			logoStr = "";
		}

		return logoStr;
	}

	@OneToMany(mappedBy = "album", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	public void addSong(Song song) {
		song.setAlbum(this);
		this.songs.add(song);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Album other = (Album) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
