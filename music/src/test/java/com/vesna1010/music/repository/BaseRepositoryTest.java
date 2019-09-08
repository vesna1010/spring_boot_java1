package com.vesna1010.music.repository;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class BaseRepositoryTest {

	public static Pageable PAGEABLE = PageRequest.of(1, 2, Sort.by("id"));

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
