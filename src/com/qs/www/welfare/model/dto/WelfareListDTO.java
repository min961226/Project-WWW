package com.qs.www.welfare.model.dto;

public class WelfareListDTO {
	private String welfareName;

	public String getWelfareName() {
		return welfareName;
	}

	public void setWelfareName(String welfareName) {
		this.welfareName = welfareName;
	}

	public WelfareListDTO(String welfareName) {
		super();
		this.welfareName = welfareName;
	}

	@Override
	public String toString() {
		return "WelfareListDTO [welfareName=" + welfareName + "]";
	}
	

	
	
	
}
