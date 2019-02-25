package com.myxq.domain;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class Goods {
	private Integer id;
	private String name;
	private Double price;
	private String image;
	@Override
	public String toString() {
		return "Goods [id=" + id + ", name=" + name + ", price=" + price + ", image=" + image + "]";
	}
	
}
