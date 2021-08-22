package com.qs.www.main.model.dto;

import java.io.Serializable;

public class AuthorityDTO implements Serializable {
	
	private String menuAuthorityCode;
	private String menuName;
	private String menuUri;
	private String menuCategory;
	private String menuCategoryUri;
	private String crudCode;
	
	public AuthorityDTO() {}

	public AuthorityDTO(String menuAuthorityCode, String menuName, String menuUri, String menuCategory,
			String menuCategoryUri, String crudCode) {
		super();
		this.menuAuthorityCode = menuAuthorityCode;
		this.menuName = menuName;
		this.menuUri = menuUri;
		this.menuCategory = menuCategory;
		this.menuCategoryUri = menuCategoryUri;
		this.crudCode = crudCode;
	}

	public String getMenuAuthorityCode() {
		return menuAuthorityCode;
	}

	public void setMenuAuthorityCode(String menuAuthorityCode) {
		this.menuAuthorityCode = menuAuthorityCode;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUri() {
		return menuUri;
	}

	public void setMenuUri(String menuUri) {
		this.menuUri = menuUri;
	}

	public String getMenuCategory() {
		return menuCategory;
	}

	public void setMenuCategory(String menuCategory) {
		this.menuCategory = menuCategory;
	}

	public String getMenuCategoryUri() {
		return menuCategoryUri;
	}

	public void setMenuCategoryUri(String menuCategoryUri) {
		this.menuCategoryUri = menuCategoryUri;
	}

	public String getCrudCode() {
		return crudCode;
	}

	public void setCrudCode(String crudCode) {
		this.crudCode = crudCode;
	}

	@Override
	public String toString() {
		return "AuthorityDTO [menuAuthorityCode=" + menuAuthorityCode + ", menuName=" + menuName + ", menuUri="
				+ menuUri + ", menuCategory=" + menuCategory + ", menuCategoryUri=" + menuCategoryUri + ", crudCode="
				+ crudCode + "]";
	}
}
