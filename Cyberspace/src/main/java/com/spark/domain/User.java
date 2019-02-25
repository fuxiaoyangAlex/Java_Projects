package com.spark.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter @Getter
@ToString(callSuper=true, includeFieldNames=true)
public class User {
	private String umail;
	private String uuid;
	private String uname;
	private String upassword;
	private String ubio;
	private String ugender;
	private String urealname;
	private String udate;
	private String uimg;

}
