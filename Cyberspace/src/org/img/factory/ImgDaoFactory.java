package org.img.factory;

import org.img.dao.Impl.ImageDaoImpl;

public class ImgDaoFactory {
	public static ImageDaoImpl getImgDaoInstance(){
		return new ImageDaoImpl();
}	
}
