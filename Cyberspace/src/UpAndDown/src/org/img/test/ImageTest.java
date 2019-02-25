package org.img.test;

import java.util.List;


import org.img.dao.ImageDao;
import org.img.domain.Image;
import org.img.domain.Use_img;
import org.img.factory.ImgDaoFactory;
import org.junit.Test;

public class ImageTest {
	ImageDao dao = ImgDaoFactory.getImgDaoInstance();
	@Test
	public void save() {
		Image image = new Image();
		image.setImg_id(5);
		image.setImg_name("ssss");
		image.setImg_address("image/5.jpg");
		image.setUse_id(2);
		dao.save(image);
	}
	@Test
	public void getImage() {
		Image image = new Image();
		image.setImg_id(1);
		Image img =dao.getAddress(1);
		System.out.println(img);
	}
	@Test
	public void getAll() {
		Use_img use = new Use_img();
		use.setId(2);
		List<Image> list = dao.getAll(2);
		System.out.println(list);
	}
}	
