package com.qs.www.mypage.model.dto;

public class ContactDTO {
	
	private String code;
	private String name;
	private String number;
	
	public ContactDTO() {}

	public ContactDTO(String code, String name, String number) {
		super();
		this.code = code;
		this.name = name;
		this.number = number;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "ContactDTO [code=" + code + ", name=" + name + ", number=" + number + "]";
	}
	
	

}
