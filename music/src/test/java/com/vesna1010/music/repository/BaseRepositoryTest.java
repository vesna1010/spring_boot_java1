package com.vesna1010.music.repository;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.vesna1010.music.MusicApplicationTests;

public abstract class BaseRepositoryTest extends MusicApplicationTests {

	@PersistenceContext
	protected EntityManager entityManager;

	public byte[] getImage() {
		File file = null;
		InputStream is = null;
		byte[] image = null;

		try {
			file = new File(getClass().getResource("/img/image.jpg").toURI());
			is = new BufferedInputStream(new FileInputStream(file));
			image = new byte[(int) file.length()];
			is.read(image);
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return image;
	}
	
	public byte[] getSong() {
		File file = null;
		InputStream is = null;
		byte[] image = null;

		try {
			file = new File(getClass().getResource("/song/song.mp3").toURI());
			is = new BufferedInputStream(new FileInputStream(file));
			image = new byte[(int) file.length()];
			is.read(image);
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return image;
	}
	

}
