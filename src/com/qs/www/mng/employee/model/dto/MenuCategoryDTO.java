package com.qs.www.mng.employee.model.dto;

public class MenuCategoryDTO {
	
	private String menuCategoryUri;
	private String menuCategoryName;
	
	public MenuCategoryDTO() {}

	public MenuCategoryDTO(String menuCategoryUri, String menuCategoryName) {
		super();
		this.menuCategoryUri = menuCategoryUri;
		this.menuCategoryName = menuCategoryName;
	}

	public String getMenuCategoryUri() {
		return menuCategoryUri;
	}

	public void setMenuCategoryUri(String menuCategoryUri) {
		this.menuCategoryUri = menuCategoryUri;
	}

	public String getMenuCategoryName() {
		return menuCategoryName;
	}

	public void setMenuCategoryName(String menuCategoryName) {
		this.menuCategoryName = menuCategoryName;
	}

	@Override
	public String toString() {
		return "MenuCategoryDTO [menuCategoryUri=" + menuCategoryUri + ", menuCategoryName=" + menuCategoryName + "]";
	}
}
