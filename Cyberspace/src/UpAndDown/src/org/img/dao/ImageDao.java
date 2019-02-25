package org.img.dao;

import java.util.List;

import org.img.domain.Image;


public interface ImageDao {
	//保存图片
	public int save(Image img);
	//根据地址获取图片
	public Image getAddress(int id);
	//获取所有图片信息
	List<Image>getAll(int use_id);
}
