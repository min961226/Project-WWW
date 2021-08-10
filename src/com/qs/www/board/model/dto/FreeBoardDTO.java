package com.qs.www.board.model.dto;

import java.awt.Image;
import java.io.Serializable;

public class FreeBoardDTO implements Serializable {
	
	private int no;
	private String title;
	
	public FreeBoardDTO() {
		
		
	}

	public FreeBoardDTO(int no, String title) {
		super();
		this.no = no;
		this.title = title;
		
		
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		
		
	}

	@Override
	public String toString() {
		return "FreeBoardDTO [no=" + no + ", title=" + title + "]";
	}
}
