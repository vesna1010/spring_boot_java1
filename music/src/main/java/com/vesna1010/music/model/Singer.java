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
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "SINGERS")
public class Singer {

	private Long id;
	private String name;
	private LocalDate birthDate;
	private byte[] photo;
	private List<Album> albums = new ArrayList<>();

	public Singer() {
	}

	public Singer(String name, LocalDate birthDate, byte[] photo) {
		this(null, name, birthDate, photo);
	}

	public Singer(Long id, String name, LocalDate birthDate, byte[] photo) {
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.photo = photo;
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

	@Column(name = "NAME", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@DateTimeFormat(pattern = "dd.MM.yyyy")
	@Column(name = "BIRTH_DATE", nullable = false)
	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	@Lob
	@Column(name = "PHOTO", nullable = false)
	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	@Transient
	public String getPhotoStr() {
		String photoStr = null;

		try {
			photoStr = new String(Base64.getEncoder().encode(this.photo), "UTF-8");
		} catch (UnsupportedEncodingException | NullPointerException e) {
			photoStr = "";
		}

		return photoStr;
	}

	@OneToMany(mappedBy = "singer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
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
		Singer other = (Singer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
