package com.qs.www.mypage.model.dto;

public class ContactDTO {
	
	private String code;
	private String name;
	private String deptname;
	private String phone;
	private String yn; 
	
	public ContactDTO() {}

	public ContactDTO(String code, String name, String deptname, String phone, String yn) {
		super();
		this.code = code;
		this.name = name;
		this.deptname = deptname;
		this.phone = phone;
		this.yn = yn;
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

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getYn() {
		return yn;
	}

	public void setYn(String yn) {
		this.yn = yn;
	}

	@Override
	public String toString() {
		return "ContactDTO [code=" + code + ", name=" + name + ", deptname=" + deptname + ", phone=" + phone + ", yn="
				+ yn + "]";
	}

	
	
	

}
