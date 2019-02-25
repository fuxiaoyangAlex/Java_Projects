package com.myxq.domain;


import lombok.Getter;
import lombok.Setter;


@Setter @Getter
public class User {
	private String uid;
	private String username;
	private String password;
	private String phone;
	
	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", password=" + password + ", phone=" + phone + "]";
	}
	
	
}
