package com.qs.www.main.model.dto;

import java.io.Serializable;

public class AuthorityDTO implements Serializable {
	
	private String menuAuthorityCode;
	private String menuUri;
	private String menuName;
	
	public AuthorityDTO() {}

	public AuthorityDTO(String menuAuthorityCode, String menuUri, String menuName) {
		super();
		this.menuAuthorityCode = menuAuthorityCode;
		this.menuUri = menuUri;
		this.menuName = menuName;
	}

	public String getMenuAuthorityCode() {
		return menuAuthorityCode;
	}

	public void setMenuAuthorityCode(String menuAuthorityCode) {
		this.menuAuthorityCode = menuAuthorityCode;
	}

	public String getMenuUri() {
		return menuUri;
	}

	public void setMenuUri(String menuUri) {
		this.menuUri = menuUri;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	@Override
	public String toString() {
		return "AuthorityDTO [menuAuthorityCode=" + menuAuthorityCode + ", menuUri=" + menuUri + ", menuName="
				+ menuName + "]";
	}
}
