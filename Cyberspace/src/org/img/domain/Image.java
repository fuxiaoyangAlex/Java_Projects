package org.img.domain;

public class Image {
	private Integer img_id;
	private String img_name;
	private String img_address;
	private int use_id;
	public Image() {
		
	}
	public Image(Integer img_id, String img_name, String img_address) {
		this.img_id = img_id;
		this.img_name = img_name;
		this.img_address = img_address;
	}
	
	public Integer getImg_id() {
		return img_id;
	}

	public void setImg_id(Integer img_id) {
		this.img_id = img_id;
	}

	public String getImg_name() {
		return img_name;
	}

	public void setImg_name(String img_name) {
		this.img_name = img_name;
	}

	public String getImg_address() {
		return img_address;
	}

	public void setImg_address(String img_address) {
		this.img_address = img_address;
	}
	
	public void setUse_id(int use_id) {
		this.use_id = use_id;
	}
	
	public int getUse_id() {
		return use_id;
	}
	
	@Override
	public String toString() {
		return img_address;
	}
}
